<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="支付商户号" prop="mchId">
        <el-select v-model="queryParams.mchId" placeholder="请选择支付商户号" clearable>
          <el-option
            v-for="dict in dict.type.pay_mch"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="交易时间" prop="recordTime">
        <el-date-picker clearable
          v-model="queryParams.recordTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择交易时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="业务名称" prop="tradeName">
        <el-select v-model="queryParams.tradeName" placeholder="请选择业务名称" clearable>
          <el-option
            v-for="dict in dict.type.pay_nfc_brand"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['pay:sum:add']"
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
          v-hasPermi="['pay:sum:edit']"
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
          v-hasPermi="['pay:sum:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pay:sum:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sumList"  show-summary  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label=" 序号" align="center"  width="50" prop="id" />
      <el-table-column label="支付商户号" align="center" prop="mchId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pay_mch" :value="scope.row.mchId"/>
        </template>
      </el-table-column>
      <el-table-column label="交易时间" align="center" prop="recordTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="业务名称" align="center" prop="tradeName">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pay_nfc_brand" :value="scope.row.tradeName"/>
        </template>
      </el-table-column>
      <el-table-column label="交易金额" align="center" prop="tradePrice" />
      <el-table-column label="退款金额" align="center" prop="refundPrice" />
      <el-table-column label="服务费" align="center" prop="serviceFee" />
      <el-table-column label="实收金额" align="center" prop="realPrice" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" prop="op" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pay:sum:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pay:sum:remove']"
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

    <!-- 添加或修改每日汇总对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="支付商户号" prop="mchId">
          <el-select v-model="form.mchId" placeholder="请选择支付商户号">
            <el-option
              v-for="dict in dict.type.pay_mch"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易时间" prop="recordTime">
          <el-date-picker clearable
            v-model="form.recordTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择交易时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="业务名称" prop="tradeName">
          <el-select v-model="form.tradeName" placeholder="请选择业务名称">
            <el-option
              v-for="dict in dict.type.pay_nfc_brand"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易金额" prop="tradePrice">
          <el-input v-model="form.tradePrice" placeholder="请输入交易金额" />
        </el-form-item>
        <el-form-item label="退款金额" prop="refundPrice">
          <el-input v-model="form.refundPrice" placeholder="请输入退款金额" />
        </el-form-item>
        <el-form-item label="服务费" prop="serviceFee">
          <el-input v-model="form.serviceFee" placeholder="请输入服务费" />
        </el-form-item>
        <el-form-item label="实收金额" prop="realPrice">
          <el-input v-model="form.realPrice" placeholder="请输入实收金额" />
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
import { listSum, getSum, delSum, addSum, updateSum } from "@/api/pay/sum";

export default {
  name: "Sum",
  dicts: ['pay_nfc_brand', 'pay_mch'],
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
      // 每日汇总表格数据
      sumList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mchId: null,
        recordTime: null,
        tradeName: null,
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
    /** 查询每日汇总列表 */
    getList() {
      this.loading = true;
      listSum(this.queryParams).then(response => {
        this.sumList = response.rows;
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
        mchId: null,
        recordTime: null,
        tradeName: null,
        tradePrice: null,
        refundPrice: null,
        serviceFee: null,
        realPrice: null,
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
      this.title = "添加每日汇总";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSum(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改每日汇总";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSum(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSum(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除每日汇总编号为"' + ids + '"的数据项？').then(function() {
        return delSum(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 不计算合计某一列 */
    totalOut(param) {
      const { columns, data } = param
      const sums = []
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '';
          return;
        }
        if (index === 1) {
          sums[index] = '合计';
          return;
        }
        const values = data.map(item => Number(item[column.property]));
        if (column.property != 'mchId' && column.property != 'recordTime'
          && column.property != 'remark' && column.property != 'op') {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return prev + curr;
            } else {
              return prev;
            }
          }, 0);
          sums[index];
        }
      });
      return sums
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pay/sum/export', {
        ...this.queryParams
      }, `sum_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
