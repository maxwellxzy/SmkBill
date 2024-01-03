<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="记账时间" prop="recordTime">
        <el-date-picker clearable
          v-model="queryParams.recordTime"
          type="date"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择记账时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="商户号" prop="mchId">
        <el-input
          v-model="queryParams.mchId"
          placeholder="请输入微信支付业务单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信支付业务单号" prop="wechatTradeNo">
        <el-input
          v-model="queryParams.wechatTradeNo"
          placeholder="请输入微信支付业务单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资金流水单号" prop="fundNo">
        <el-input
          v-model="queryParams.fundNo"
          placeholder="请输入资金流水单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务名称" prop="tradeName">
        <el-input
          v-model="queryParams.tradeName"
          placeholder="请输入业务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收支金额(元)" prop="fundPrice">
        <el-input
          v-model="queryParams.fundPrice"
          placeholder="请输入收支金额(元)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="业务凭证号" prop="voucherNo">
        <el-input
          v-model="queryParams.voucherNo"
          placeholder="请输入业务凭证号"
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
          v-hasPermi="['pay:wefund:add']"
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
          v-hasPermi="['pay:wefund:edit']"
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
          v-hasPermi="['pay:wefund:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pay:wefund:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wefundList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="columnId">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="记账时间" align="center" prop="recordTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商户号" align="center" prop="mchId" />
      <el-table-column label="微信支付业务单号" align="center" prop="wechatTradeNo" />
      <el-table-column label="资金流水单号" align="center" prop="fundNo" />
      <el-table-column label="业务名称" align="center" prop="tradeName" />
      <el-table-column label="业务类型" align="center" prop="tradeType" />
      <el-table-column label="收支类型" align="center" prop="inOutType" />
      <el-table-column label="收支金额(元)" align="center" prop="fundPrice" />
      <el-table-column label="账户结余(元)" align="center" prop="accountBalance" />
<!--      <el-table-column label="资金变更提交申请人" align="center" prop="fundUser" />-->
<!--      <el-table-column label="备注" align="center" prop="remark" />-->
      <el-table-column label="业务凭证号" align="center" prop="voucherNo" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pay:wefund:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pay:wefund:remove']"
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

    <!-- 添加或修改微信支付资金账单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="记账时间" prop="recordTime">
          <el-date-picker clearable
            v-model="form.recordTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记账时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="商户号" prop="mchId">
          <el-input v-model="form.mchId" placeholder="请输入商户号" />
        </el-form-item>
        <el-form-item label="微信支付业务单号" prop="wechatTradeNo">
          <el-input v-model="form.wechatTradeNo" placeholder="请输入微信支付业务单号" />
        </el-form-item>
        <el-form-item label="资金流水单号" prop="fundNo">
          <el-input v-model="form.fundNo" placeholder="请输入资金流水单号" />
        </el-form-item>
        <el-form-item label="业务名称" prop="tradeName">
          <el-input v-model="form.tradeName" placeholder="请输入业务名称" />
        </el-form-item>
        <el-form-item label="收支金额(元)" prop="fundPrice">
          <el-input v-model="form.fundPrice" placeholder="请输入收支金额(元)" />
        </el-form-item>
        <el-form-item label="账户结余(元)" prop="accountBalance">
          <el-input v-model="form.accountBalance" placeholder="请输入账户结余(元)" />
        </el-form-item>
        <el-form-item label="资金变更提交申请人" prop="fundUser">
          <el-input v-model="form.fundUser" placeholder="请输入资金变更提交申请人" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="业务凭证号" prop="voucherNo">
          <el-input v-model="form.voucherNo" placeholder="请输入业务凭证号" />
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
import { listWefund, getWefund, delWefund, addWefund, updateWefund } from "@/api/pay/wefund";

export default {
  name: "Wefund",
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
      // 微信支付资金账单表格数据
      wefundList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordTime: null,
        mchId: null,
        wechatTradeNo: null,
        fundNo: null,
        tradeName: null,
        tradeType: null,
        inOutType: null,
        fundPrice: null,
        accountBalance: null,
        fundUser: null,
        voucherNo: null
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
    /** 查询微信支付资金账单列表 */
    getList() {
      this.loading = true;
      listWefund(this.queryParams).then(response => {
        this.wefundList = response.rows;
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
        recordTime: null,
        mchId: null,
        wechatTradeNo: null,
        fundNo: null,
        tradeName: null,
        tradeType: null,
        inOutType: null,
        fundPrice: null,
        accountBalance: null,
        fundUser: null,
        remark: null,
        voucherNo: null
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
      this.title = "添加微信支付资金账单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const columnId = row.columnId || this.ids
      getWefund(columnId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改微信支付资金账单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.columnId != null) {
            updateWefund(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWefund(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除微信支付资金账单编号为"' + columnIds + '"的数据项？').then(function() {
        return delWefund(columnIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pay/wefund/export', {
        ...this.queryParams
      }, `wefund_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
