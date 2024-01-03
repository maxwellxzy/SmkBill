<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="交易时间" prop="tradeTime">
        <el-date-picker clearable
          v-model="queryParams.tradeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择交易时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="公众账号ID" prop="mpId">
        <el-input
          v-model="queryParams.mpId"
          placeholder="请输入公众账号ID"
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
      <el-form-item label="特约商户号" prop="vipMchId">
        <el-input
          v-model="queryParams.vipMchId"
          placeholder="请输入特约商户号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备号" prop="terminalId">
        <el-input
          v-model="queryParams.terminalId"
          placeholder="请输入设备号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信订单号" prop="wechatTradeNo">
        <el-input
          v-model="queryParams.wechatTradeNo"
          placeholder="请输入微信订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商户订单号" prop="mchTradeNo">
        <el-input
          v-model="queryParams.mchTradeNo"
          placeholder="请输入商户订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户标识" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="付款银行" prop="payBank">
        <el-input
          v-model="queryParams.payBank"
          placeholder="请输入付款银行"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应结订单金额" prop="tradePayable">
        <el-input
          v-model="queryParams.tradePayable"
          placeholder="请输入应结订单金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代金券金额" prop="couponPrice">
        <el-input
          v-model="queryParams.couponPrice"
          placeholder="请输入代金券金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信退款单号" prop="wechatRefundNo">
        <el-input
          v-model="queryParams.wechatRefundNo"
          placeholder="请输入微信退款单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商户退款单号" prop="mchRefundNo">
        <el-input
          v-model="queryParams.mchRefundNo"
          placeholder="请输入商户退款单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款金额" prop="refundPrice">
        <el-input
          v-model="queryParams.refundPrice"
          placeholder="请输入退款金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="充值券退款金额" prop="couponRefundPrice">
        <el-input
          v-model="queryParams.couponRefundPrice"
          placeholder="请输入充值券退款金额"
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
      <el-form-item label="商户数据包" prop="mchDataPackage">
        <el-input
          v-model="queryParams.mchDataPackage"
          placeholder="请输入商户数据包"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手续费" prop="serviceFee">
        <el-input
          v-model="queryParams.serviceFee"
          placeholder="请输入手续费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="费率" prop="serviceRate">
        <el-input
          v-model="queryParams.serviceRate"
          placeholder="请输入费率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单金额" prop="tradePrice">
        <el-input
          v-model="queryParams.tradePrice"
          placeholder="请输入订单金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请退款金额" prop="requestRefund">
        <el-input
          v-model="queryParams.requestRefund"
          placeholder="请输入申请退款金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="费率备注" prop="rateRemark">
        <el-input
          v-model="queryParams.rateRemark"
          placeholder="请输入费率备注"
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
          v-hasPermi="['pay:wetrade:add']"
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
          v-hasPermi="['pay:wetrade:edit']"
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
          v-hasPermi="['pay:wetrade:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pay:wetrade:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wetradeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="columnId" />
      <el-table-column label="交易时间" align="center" prop="tradeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.tradeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="公众账号ID" align="center" prop="mpId" />
      <el-table-column label="商户号" align="center" prop="mchId" />
<!--      <el-table-column label="特约商户号" align="center" prop="vipMchId" />-->
<!--      <el-table-column label="设备号" align="center" prop="terminalId" />-->
      <el-table-column label="微信订单号" align="center" prop="wechatTradeNo" />
      <el-table-column label="商户订单号" align="center" prop="mchTradeNo" />
      <el-table-column label="用户标识" align="center" prop="userId" />
      <el-table-column label="交易类型" align="center" prop="tradeType" />
      <el-table-column label="交易状态" align="center" prop="tradeStatus" />
      <el-table-column label="付款银行" align="center" prop="payBank" />
      <el-table-column label="货币种类" align="center" prop="currencyType" />
      <el-table-column label="应结订单金额" align="center" prop="tradePayable" />
      <el-table-column label="代金券金额" align="center" prop="couponPrice" />
      <el-table-column label="微信退款单号" align="center" prop="wechatRefundNo" />
      <el-table-column label="商户退款单号" align="center" prop="mchRefundNo" />
      <el-table-column label="退款金额" align="center" prop="refundPrice" />
      <el-table-column label="充值券退款金额" align="center" prop="couponRefundPrice" />
      <el-table-column label="退款类型" align="center" prop="refundType" />
      <el-table-column label="退款状态" align="center" prop="refundStatus" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="商户数据包" align="center" prop="mchDataPackage" />
      <el-table-column label="手续费" align="center" prop="serviceFee" />
      <el-table-column label="费率" align="center" prop="serviceRate" />
      <el-table-column label="订单金额" align="center" prop="tradePrice" />
      <el-table-column label="申请退款金额" align="center" prop="requestRefund" />
      <el-table-column label="费率备注" align="center" prop="rateRemark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pay:wetrade:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pay:wetrade:remove']"
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

    <!-- 添加或修改微信交易订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="交易时间" prop="tradeTime">
          <el-date-picker clearable
            v-model="form.tradeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择交易时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="公众账号ID" prop="mpId">
          <el-input v-model="form.mpId" placeholder="请输入公众账号ID" />
        </el-form-item>
        <el-form-item label="商户号" prop="mchId">
          <el-input v-model="form.mchId" placeholder="请输入商户号" />
        </el-form-item>
        <el-form-item label="特约商户号" prop="vipMchId">
          <el-input v-model="form.vipMchId" placeholder="请输入特约商户号" />
        </el-form-item>
        <el-form-item label="设备号" prop="terminalId">
          <el-input v-model="form.terminalId" placeholder="请输入设备号" />
        </el-form-item>
        <el-form-item label="微信订单号" prop="wechatTradeNo">
          <el-input v-model="form.wechatTradeNo" placeholder="请输入微信订单号" />
        </el-form-item>
        <el-form-item label="商户订单号" prop="mchTradeNo">
          <el-input v-model="form.mchTradeNo" placeholder="请输入商户订单号" />
        </el-form-item>
        <el-form-item label="用户标识" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户标识" />
        </el-form-item>
        <el-form-item label="付款银行" prop="payBank">
          <el-input v-model="form.payBank" placeholder="请输入付款银行" />
        </el-form-item>
        <el-form-item label="应结订单金额" prop="tradePayable">
          <el-input v-model="form.tradePayable" placeholder="请输入应结订单金额" />
        </el-form-item>
        <el-form-item label="代金券金额" prop="couponPrice">
          <el-input v-model="form.couponPrice" placeholder="请输入代金券金额" />
        </el-form-item>
        <el-form-item label="微信退款单号" prop="wechatRefundNo">
          <el-input v-model="form.wechatRefundNo" placeholder="请输入微信退款单号" />
        </el-form-item>
        <el-form-item label="商户退款单号" prop="mchRefundNo">
          <el-input v-model="form.mchRefundNo" placeholder="请输入商户退款单号" />
        </el-form-item>
        <el-form-item label="退款金额" prop="refundPrice">
          <el-input v-model="form.refundPrice" placeholder="请输入退款金额" />
        </el-form-item>
        <el-form-item label="充值券退款金额" prop="couponRefundPrice">
          <el-input v-model="form.couponRefundPrice" placeholder="请输入充值券退款金额" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商户数据包" prop="mchDataPackage">
          <el-input v-model="form.mchDataPackage" placeholder="请输入商户数据包" />
        </el-form-item>
        <el-form-item label="手续费" prop="serviceFee">
          <el-input v-model="form.serviceFee" placeholder="请输入手续费" />
        </el-form-item>
        <el-form-item label="费率" prop="serviceRate">
          <el-input v-model="form.serviceRate" placeholder="请输入费率" />
        </el-form-item>
        <el-form-item label="订单金额" prop="tradePrice">
          <el-input v-model="form.tradePrice" placeholder="请输入订单金额" />
        </el-form-item>
        <el-form-item label="申请退款金额" prop="requestRefund">
          <el-input v-model="form.requestRefund" placeholder="请输入申请退款金额" />
        </el-form-item>
        <el-form-item label="费率备注" prop="rateRemark">
          <el-input v-model="form.rateRemark" placeholder="请输入费率备注" />
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
import { listWetrade, getWetrade, delWetrade, addWetrade, updateWetrade } from "@/api/pay/wetrade";

export default {
  name: "Wetrade",
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
      // 微信交易订单表格数据
      wetradeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tradeTime: null,
        mpId: null,
        mchId: null,
        vipMchId: null,
        terminalId: null,
        wechatTradeNo: null,
        mchTradeNo: null,
        userId: null,
        tradeType: null,
        tradeStatus: null,
        payBank: null,
        currencyType: null,
        tradePayable: null,
        couponPrice: null,
        wechatRefundNo: null,
        mchRefundNo: null,
        refundPrice: null,
        couponRefundPrice: null,
        refundType: null,
        refundStatus: null,
        goodsName: null,
        mchDataPackage: null,
        serviceFee: null,
        serviceRate: null,
        tradePrice: null,
        requestRefund: null,
        rateRemark: null
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
    /** 查询微信交易订单列表 */
    getList() {
      this.loading = true;
      listWetrade(this.queryParams).then(response => {
        this.wetradeList = response.rows;
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
        tradeTime: null,
        mpId: null,
        mchId: null,
        vipMchId: null,
        terminalId: null,
        wechatTradeNo: null,
        mchTradeNo: null,
        userId: null,
        tradeType: null,
        tradeStatus: null,
        payBank: null,
        currencyType: null,
        tradePayable: null,
        couponPrice: null,
        wechatRefundNo: null,
        mchRefundNo: null,
        refundPrice: null,
        couponRefundPrice: null,
        refundType: null,
        refundStatus: null,
        goodsName: null,
        mchDataPackage: null,
        serviceFee: null,
        serviceRate: null,
        tradePrice: null,
        requestRefund: null,
        rateRemark: null
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
      this.title = "添加微信交易订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const columnId = row.columnId || this.ids
      getWetrade(columnId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改微信交易订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.columnId != null) {
            updateWetrade(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWetrade(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除微信交易订单编号为"' + columnIds + '"的数据项？').then(function() {
        return delWetrade(columnIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pay/wetrade/export', {
        ...this.queryParams
      }, `wetrade_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
