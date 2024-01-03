<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型" prop="mchType">
        <el-select v-model="queryParams.mchType" placeholder="请选择类型" clearable>
          <el-option
            v-for="dict in dict.type.pay_trade_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商户号ID" prop="mchId">
        <el-input
          v-model="queryParams.mchId"
          placeholder="请输入商户号ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商户号名称" prop="mchName">
        <el-input
          v-model="queryParams.mchName"
          placeholder="请输入商户号名称"
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
          v-hasPermi="['pay:paymchid:add']"
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
          v-hasPermi="['pay:paymchid:edit']"
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
          v-hasPermi="['pay:paymchid:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pay:paymchid:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="paymchidList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="类型" align="center" prop="mchType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pay_trade_type" :value="scope.row.mchType"/>
        </template>
      </el-table-column>
      <el-table-column label="商户号ID" align="center" prop="mchId" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="商户号名称" align="center" prop="mchName" />
<!--      <el-table-column label="api key" align="center" prop="apiKey" />-->
<!--      <el-table-column label="证书序列号" align="center" prop="snKey" />-->
<!--      <el-table-column label="微信证书路径" align="center" prop="wechatApiCert" />-->
<!--      <el-table-column label="支付宝公钥路径" align="center" prop="alipayCertPath" />-->
<!--      <el-table-column label="支付宝应用私钥" align="center" prop="alipayappPrivatePath" />-->
<!--      <el-table-column label="支付宝应用公钥证书" align="center" prop="alipayappPublicPath" />-->
<!--      <el-table-column label="alipay根证书" align="center" prop="alipayRootPath" />-->
<!--      <el-table-column label="备用证书1" align="center" prop="b1Key" />-->
<!--      <el-table-column label="备用证书2" align="center" prop="b2Key" />-->
<!--      <el-table-column label="备注" align="center" prop="remark" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pay:paymchid:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pay:paymchid:remove']"
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

    <!-- 添加或修改商户号管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型" prop="mchType">
          <el-select v-model="form.mchType" placeholder="请选择类型">
            <el-option
              v-for="dict in dict.type.pay_trade_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商户号ID" prop="mchId">
          <el-input v-model="form.mchId" placeholder="请输入商户号ID" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="商户号名称" prop="mchName">
          <el-input v-model="form.mchName" placeholder="请输入商户号名称" />
        </el-form-item>
        <el-form-item label="api key" prop="apiKey">
          <el-input v-model="form.apiKey" placeholder="请输入api key" />
        </el-form-item>
        <el-form-item label="证书序列号" prop="snKey">
          <el-input v-model="form.snKey" placeholder="请输入证书序列号" />
        </el-form-item>
        <el-form-item label="微信证书路径" prop="wechatApiCert">
          <el-input v-model="form.wechatApiCert" placeholder="请输入微信证书路径" />
        </el-form-item>
        <el-form-item label="支付宝公钥路径" prop="alipayCertPath">
          <el-input v-model="form.alipayCertPath" placeholder="请输入支付宝公钥路径" />
        </el-form-item>
        <el-form-item label="支付宝应用私钥" prop="alipayappPrivatePath">
          <el-input v-model="form.alipayappPrivatePath" placeholder="请输入支付宝应用私钥" />
        </el-form-item>
        <el-form-item label="支付宝应用公钥证书" prop="alipayappPublicPath">
          <el-input v-model="form.alipayappPublicPath" placeholder="请输入支付宝应用公钥证书" />
        </el-form-item>
        <el-form-item label="alipay根证书" prop="alipayRootPath">
          <el-input v-model="form.alipayRootPath" placeholder="请输入alipay根证书" />
        </el-form-item>
        <el-form-item label="备用证书1" prop="b1Key">
          <el-input v-model="form.b1Key" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备用证书2" prop="b2Key">
          <el-input v-model="form.b2Key" type="textarea" placeholder="请输入内容" />
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
import { listPaymchid, getPaymchid, delPaymchid, addPaymchid, updatePaymchid } from "@/api/pay/paymchid";

export default {
  name: "Paymchid",
  dicts: ['pay_trade_type', 'sys_normal_disable'],
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
      // 商户号管理表格数据
      paymchidList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mchType: null,
        mchId: null,
        status: null,
        mchName: null,
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
    /** 查询商户号管理列表 */
    getList() {
      this.loading = true;
      listPaymchid(this.queryParams).then(response => {
        this.paymchidList = response.rows;
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
        id: null,
        mchType: null,
        mchId: null,
        status: null,
        mchName: null,
        apiKey: null,
        snKey: null,
        wechatApiCert: null,
        alipayCertPath: null,
        alipayappPrivatePath: null,
        alipayappPublicPath: null,
        alipayRootPath: null,
        b1Key: null,
        b2Key: null,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商户号管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPaymchid(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商户号管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePaymchid(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPaymchid(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除商户号管理编号为"' + ids + '"的数据项？').then(function() {
        return delPaymchid(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pay/paymchid/export', {
        ...this.queryParams
      }, `paymchid_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
