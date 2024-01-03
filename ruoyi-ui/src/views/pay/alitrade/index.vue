<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="支付宝交易号" prop="alipayTradeNo">
        <el-input
          v-model="queryParams.alipayTradeNo"
          placeholder="请输入支付宝交易号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="商户订单号" prop="merTradeNo">
        <el-input
          v-model="queryParams.merTradeNo"
          placeholder="请输入商户订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商户号" prop="mchId">
        <el-input
          v-model="queryParams.mchId"
          placeholder="请输入商户号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易类型" prop="tradeType">
        <el-input
          v-model="queryParams.tradeType"
          placeholder="请输入交易类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="完成时间" prop="completeTime">
        <el-date-picker clearable
          v-model="queryParams.completeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择完成时间">
        </el-date-picker>
      </el-form-item>


      <el-form-item label="对方账户" prop="userAccount">
        <el-input
          v-model="queryParams.userAccount"
          placeholder="请输入对方账户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单金额" prop="orderPrice">
        <el-input
          v-model="queryParams.orderPrice"
          placeholder="请输入订单金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商家实收" prop="realPrice">
        <el-input
          v-model="queryParams.realPrice"
          placeholder="请输入商家实收"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item label="支付宝优惠(元)" prop="alipayCoupon">
        <el-input
          v-model="queryParams.alipayCoupon"
          placeholder="请输入支付宝优惠(元)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商家优惠" prop="merCoupon">
        <el-input
          v-model="queryParams.merCoupon"
          placeholder="请输入商家优惠"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="券核销金额" prop="couponCompletePrice">
        <el-input
          v-model="queryParams.couponCompletePrice"
          placeholder="请输入券核销金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="商家红包消费金额" prop="merRedpocketComsume">
        <el-input
          v-model="queryParams.merRedpocketComsume"
          placeholder="请输入商家红包消费金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卡消费金额" prop="cardConsume">
        <el-input
          v-model="queryParams.cardConsume"
          placeholder="请输入卡消费金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款批次号/请求号" prop="refundNo">
        <el-input
          v-model="queryParams.refundNo"
          placeholder="请输入退款批次号/请求号"
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
          v-hasPermi="['pay:alitrade:add']"
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
          v-hasPermi="['pay:alitrade:edit']"
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
          v-hasPermi="['pay:alitrade:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pay:alitrade:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="alitradeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="columnId" />
      <el-table-column label="支付宝交易号" align="center" prop="alipayTradeNo" />
      <el-table-column label="商户订单号" align="center" prop="merTradeNo" />
      <el-table-column label="商户号" align="center" prop="mchId" />
      <el-table-column label="业务类型" align="center" prop="tradeType" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="完成时间" align="center" prop="completeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="对方账户" align="center" prop="userAccount" />
      <el-table-column label="订单金额" align="center" prop="orderPrice" />
      <el-table-column label="商家实收" align="center" prop="realPrice" />

      <el-table-column label="退款批次号/请求号" align="center" prop="refundNo" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pay:alitrade:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pay:alitrade:remove']"
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

    <!-- 添加或修改支付宝交易明细单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="支付宝交易号" prop="alipayTradeNo">
          <el-input v-model="form.alipayTradeNo" placeholder="请输入支付宝交易号" />
        </el-form-item>
        <el-form-item label="商户订单号" prop="merTradeNo">
          <el-input v-model="form.merTradeNo" placeholder="请输入商户订单号" />
        </el-form-item>
        <el-form-item label="商户号" prop="mchId">
          <el-input v-model="form.mchId" placeholder="请输入商户号" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="完成时间" prop="completeTime">
          <el-date-picker clearable
            v-model="form.completeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="门店编号" prop="retailNo">
          <el-input v-model="form.retailNo" placeholder="请输入门店编号" />
        </el-form-item>
        <el-form-item label="门店名称" prop="retailName">
          <el-input v-model="form.retailName" placeholder="请输入门店名称" />
        </el-form-item>
        <el-form-item label="操作员" prop="operator">
          <el-input v-model="form.operator" placeholder="请输入操作员" />
        </el-form-item>
        <el-form-item label="终端号" prop="terminalNo">
          <el-input v-model="form.terminalNo" placeholder="请输入终端号" />
        </el-form-item>
        <el-form-item label="对方账户" prop="userAccount">
          <el-input v-model="form.userAccount" placeholder="请输入对方账户" />
        </el-form-item>
        <el-form-item label="订单金额" prop="orderPrice">
          <el-input v-model="form.orderPrice" placeholder="请输入订单金额" />
        </el-form-item>
        <el-form-item label="商家实收" prop="realPrice">
          <el-input v-model="form.realPrice" placeholder="请输入商家实收" />
        </el-form-item>
        <el-form-item label="支付宝红包" prop="alipayRedpocket">
          <el-input v-model="form.alipayRedpocket" placeholder="请输入支付宝红包" />
        </el-form-item>
        <el-form-item label="集分宝" prop="jifenbao">
          <el-input v-model="form.jifenbao" placeholder="请输入集分宝" />
        </el-form-item>
        <el-form-item label="支付宝优惠(元)" prop="alipayCoupon">
          <el-input v-model="form.alipayCoupon" placeholder="请输入支付宝优惠(元)" />
        </el-form-item>
        <el-form-item label="商家优惠" prop="merCoupon">
          <el-input v-model="form.merCoupon" placeholder="请输入商家优惠" />
        </el-form-item>
        <el-form-item label="券核销金额" prop="couponCompletePrice">
          <el-input v-model="form.couponCompletePrice" placeholder="请输入券核销金额" />
        </el-form-item>
        <el-form-item label="券名称" prop="couponName">
          <el-input v-model="form.couponName" placeholder="请输入券名称" />
        </el-form-item>
        <el-form-item label="商家红包消费金额" prop="merRedpocketComsume">
          <el-input v-model="form.merRedpocketComsume" placeholder="请输入商家红包消费金额" />
        </el-form-item>
        <el-form-item label="卡消费金额" prop="cardConsume">
          <el-input v-model="form.cardConsume" placeholder="请输入卡消费金额" />
        </el-form-item>
        <el-form-item label="退款批次号/请求号" prop="refundNo">
          <el-input v-model="form.refundNo" placeholder="请输入退款批次号/请求号" />
        </el-form-item>
        <el-form-item label="服务费" prop="serviceFee">
          <el-input v-model="form.serviceFee" placeholder="请输入服务费" />
        </el-form-item>
        <el-form-item label="分润" prop="profit">
          <el-input v-model="form.profit" placeholder="请输入分润" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listAlitrade, getAlitrade, delAlitrade, addAlitrade, updateAlitrade } from "@/api/pay/alitrade";

export default {
  name: "Alitrade",
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
      // 支付宝交易明细单表格数据
      alitradeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        alipayTradeNo: null,
        merTradeNo: null,
        mchId: null,
        tradeType: null,
        goodsName: null,
        completeTime: null,
        retailNo: null,
        retailName: null,
        operator: null,
        terminalNo: null,
        userAccount: null,
        orderPrice: null,
        realPrice: null,
        alipayRedpocket: null,
        jifenbao: null,
        alipayCoupon: null,
        merCoupon: null,
        couponCompletePrice: null,
        couponName: null,
        merRedpocketComsume: null,
        cardConsume: null,
        refundNo: null,
        serviceFee: null,
        profit: null,
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
    /** 查询支付宝交易明细单列表 */
    getList() {
      this.loading = true;
      listAlitrade(this.queryParams).then(response => {
        this.alitradeList = response.rows;
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
        alipayTradeNo: null,
        merTradeNo: null,
        mchId: null,
        tradeType: null,
        goodsName: null,
        createTime: null,
        completeTime: null,
        retailNo: null,
        retailName: null,
        operator: null,
        terminalNo: null,
        userAccount: null,
        orderPrice: null,
        realPrice: null,
        alipayRedpocket: null,
        jifenbao: null,
        alipayCoupon: null,
        merCoupon: null,
        couponCompletePrice: null,
        couponName: null,
        merRedpocketComsume: null,
        cardConsume: null,
        refundNo: null,
        serviceFee: null,
        profit: null,
        remark: null
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
      this.title = "添加支付宝交易明细单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const columnId = row.columnId || this.ids
      getAlitrade(columnId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改支付宝交易明细单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.columnId != null) {
            updateAlitrade(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAlitrade(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除支付宝交易明细单编号为"' + columnIds + '"的数据项？').then(function() {
        return delAlitrade(columnIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pay/alitrade/export', {
        ...this.queryParams
      }, `alitrade_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
