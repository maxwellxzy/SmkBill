<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="入帐时间" prop="fundDate">
        <el-date-picker clearable
          v-model="queryParams.fundDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择入帐时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="商户号" prop="mchId">
        <el-input
          v-model="queryParams.mchId"
          placeholder="请输入商户号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店编号" prop="retailNo">
        <el-input
          v-model="queryParams.retailNo"
          placeholder="请输入门店编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店名称 " prop="retailName">
        <el-input
          v-model="queryParams.retailName"
          placeholder="请输入门店名称 "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易订单总笔数" prop="tradeNoSum">
        <el-input
          v-model="queryParams.tradeNoSum"
          placeholder="请输入交易订单总笔数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款订单总笔数" prop="refundNoSum">
        <el-input
          v-model="queryParams.refundNoSum"
          placeholder="请输入退款订单总笔数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单金额" prop="tradePriceSum">
        <el-input
          v-model="queryParams.tradePriceSum"
          placeholder="请输入订单金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商家实收" prop="merRealSum">
        <el-input
          v-model="queryParams.merRealSum"
          placeholder="请输入商家实收"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支付宝优惠" prop="alipayCouponSum">
        <el-input
          v-model="queryParams.alipayCouponSum"
          placeholder="请输入支付宝优惠"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商家优惠" prop="merCouponSum">
        <el-input
          v-model="queryParams.merCouponSum"
          placeholder="请输入商家优惠"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卡消费金额" prop="cardPriceSum">
        <el-input
          v-model="queryParams.cardPriceSum"
          placeholder="请输入卡消费金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务费" prop="serviceFeeSum">
        <el-input
          v-model="queryParams.serviceFeeSum"
          placeholder="请输入服务费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分润" prop="profitSum">
        <el-input
          v-model="queryParams.profitSum"
          placeholder="请输入分润"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实收净额" prop="realIncomeSum">
        <el-input
          v-model="queryParams.realIncomeSum"
          placeholder="请输入实收净额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['pay:alifundsum:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pay:alifundsum:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pay:alifundsum:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pay:alifundsum:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="alifundsumList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="columnId" />
      <el-table-column label="入帐时间" align="center" prop="fundDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.fundDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商户号" align="center" prop="mchId" />
<!--      <el-table-column label="门店编号" align="center" prop="retailNo" />-->
<!--      <el-table-column label="门店名称 " align="center" prop="retailName" />-->
      <el-table-column label="交易订单总笔数" align="center" prop="tradeNoSum" />
      <el-table-column label="退款订单总笔数" align="center" prop="refundNoSum" />
      <el-table-column label="订单金额" align="center" prop="tradePriceSum" />
      <el-table-column label="商家实收" align="center" prop="merRealSum" />
      <el-table-column label="支付宝优惠" align="center" prop="alipayCouponSum" />
      <el-table-column label="商家优惠" align="center" prop="merCouponSum" />
      <el-table-column label="卡消费金额" align="center" prop="cardPriceSum" />
      <el-table-column label="服务费" align="center" prop="serviceFeeSum" />
<!--      <el-table-column label="分润" align="center" prop="profitSum" />-->
      <el-table-column label="实收净额" align="center" prop="realIncomeSum" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pay:alifundsum:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pay:alifundsum:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改支付宝资金汇总对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="入帐时间" prop="fundDate">
          <el-date-picker clearable
            v-model="form.fundDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择入帐时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="商户号" prop="mchId">
          <el-input v-model="form.mchId" placeholder="请输入商户号" />
        </el-form-item>
<!--        <el-form-item label="门店编号" prop="retailNo">-->
<!--          <el-input v-model="form.retailNo" placeholder="请输入门店编号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="门店名称 " prop="retailName">-->
<!--          <el-input v-model="form.retailName" placeholder="请输入门店名称 " />-->
<!--        </el-form-item>-->
        <el-form-item label="交易订单总笔数" prop="tradeNoSum">
          <el-input v-model="form.tradeNoSum" placeholder="请输入交易订单总笔数" />
        </el-form-item>
        <el-form-item label="退款订单总笔数" prop="refundNoSum">
          <el-input v-model="form.refundNoSum" placeholder="请输入退款订单总笔数" />
        </el-form-item>
        <el-form-item label="订单金额" prop="tradePriceSum">
          <el-input v-model="form.tradePriceSum" placeholder="请输入订单金额" />
        </el-form-item>
        <el-form-item label="商家实收" prop="merRealSum">
          <el-input v-model="form.merRealSum" placeholder="请输入商家实收" />
        </el-form-item>
        <el-form-item label="支付宝优惠" prop="alipayCouponSum">
          <el-input v-model="form.alipayCouponSum" placeholder="请输入支付宝优惠" />
        </el-form-item>
        <el-form-item label="商家优惠" prop="merCouponSum">
          <el-input v-model="form.merCouponSum" placeholder="请输入商家优惠" />
        </el-form-item>
        <el-form-item label="卡消费金额" prop="cardPriceSum">
          <el-input v-model="form.cardPriceSum" placeholder="请输入卡消费金额" />
        </el-form-item>
        <el-form-item label="服务费" prop="serviceFeeSum">
          <el-input v-model="form.serviceFeeSum" placeholder="请输入服务费" />
        </el-form-item>
<!--        <el-form-item label="分润" prop="profitSum">-->
<!--          <el-input v-model="form.profitSum" placeholder="请输入分润" />-->
<!--        </el-form-item>-->
        <el-form-item label="实收净额" prop="realIncomeSum">
          <el-input v-model="form.realIncomeSum" placeholder="请输入实收净额" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAlifundsum, getAlifundsum, delAlifundsum, addAlifundsum, updateAlifundsum } from "@/api/pay/alifundsum";

export default {
  name: "Alifundsum",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 支付宝资金汇总表格数据
      alifundsumList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fundDate: null,
        mchId: null,
        retailNo: null,
        retailName: null,
        tradeNoSum: null,
        refundNoSum: null,
        tradePriceSum: null,
        merRealSum: null,
        alipayCouponSum: null,
        merCouponSum: null,
        cardPriceSum: null,
        serviceFeeSum: null,
        profitSum: null,
        realIncomeSum: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询支付宝资金汇总列表 */
    getList() {
      this.loading = true;
      listAlifundsum(this.queryParams).then(response => {
        this.alifundsumList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        columnId: null,
        fundDate: null,
        mchId: null,
        retailNo: null,
        retailName: null,
        tradeNoSum: null,
        refundNoSum: null,
        tradePriceSum: null,
        merRealSum: null,
        alipayCouponSum: null,
        merCouponSum: null,
        cardPriceSum: null,
        serviceFeeSum: null,
        profitSum: null,
        realIncomeSum: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.columnId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加支付宝资金汇总";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const columnId = row.columnId || this.ids
      getAlifundsum(columnId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改支付宝资金汇总";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.columnId != null) {
            updateAlifundsum(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAlifundsum(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const columnIds = row.columnId || this.ids;
      this.$modal.confirm('是否确认删除支付宝资金汇总编号为"' + columnIds + '"的数据项？').then(function() {
        return delAlifundsum(columnIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pay/alifundsum/export', {
        ...this.queryParams
      }, `alifundsum_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
