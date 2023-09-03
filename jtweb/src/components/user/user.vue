<template>
  <div>
    <!--编辑面包屑导航-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!--2.定义卡片标签-->
    <el-card class="box-card">
      <!--定义一行元素-->
      <el-row :gutter="20">
        <el-col :span="9">
          <!--定义用户的输入框-->
          <el-input placeholder="请输入内容" v-model="queryInfo.query" class="input-with-select" clearable
            @clear="getUserList()">
            <el-button slot="append" icon="el-icon-search" @click="getUserList()"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <!-- 定义新增用户按钮 -->
          <el-button type="primary" @click="addUserDialogVisible = true">添加用户</el-button>
        </el-col>
      </el-row>
      <!--定义表格数据
        :data 表格数据的来源
        prop: 从userList中获取属性值
        label: 字段名称
      -->
      <el-table :data="userList" style="width: 100%" stripe border>
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="phone" label="电话"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="status" label="状态">
          <!-- 作用域插槽 -->
          <template slot-scope="scope">
            <!-- scope封装的对象 获取行级元素 row属于实行-->
            <!-- {{ scope.row.status }} -->
            <el-switch v-model="scope.row.status" @change="updateStatus(scope.row)" active-color="#13ce66"
              inactive-color="#ff4949"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="small" @click="updateUserBtn(scope.row)"></el-button>
            <el-button type="info" size="small" @click="deleteUser(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--
          定义分页功能
          1.@size-change    页面条数变化的时候触发
          2.@current-change 当页面改变的时候触发
          3.:current-page   显示当前的页数
          4.:page-sizes     显示页数的数组
          5.:page-size      初始条件下每页的条数
          6.layout          显示分页的样式总类
          7.total           数据的总数
      -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum" :page-sizes="[1, 5, 10, 20]" :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </el-card>
    <!-- :visible.sync="addUserDialogVisible" 控制对话框是否展现-->
    <el-dialog title="用户新增" :visible.sync="addUserDialogVisible" width="45%" @close="closeAddUserDialog">
      <el-form :model="addUSerForm" :rules="rules" ref="addUserRuleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addUSerForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addUSerForm.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="password2">
          <el-input v-model="addUSerForm.password2"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="addUSerForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addUSerForm.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addUserDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUserBtn">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="更新用户" :visible.sync="updateDialogVisible" width="45%">
      <el-form :model="updateUserForm" :rules="rules" ref="updateUserRuleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="updateUserForm.username"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="updateUserForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="updateUserForm.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUser">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    /**
     * 自定义校验规则
     * rule: 当前对象一般不用
     * value: 校验的数据
     * callback: 回调函数
     * 校验规则
     */
    const checkEmail = (rule, value, callback) => {
      //定义邮箱的正则表达式  JS中用/来表示正则表达式的开始和结束
      const emailRege = /^[a-zA-Z0-9_]+@[a-zA-Z0-9-_]+\.[a-zA-Z0-9-_]+$/
      if (emailRege.test(value)) {
        //表示邮箱合法  正确通过了返回true  失败返回false
        return callback()//自定义规则执行成功
      }
      callback(new Error('请填写正确的邮箱地址'))
    }
    //校验手机号
    const checkPhone = (rule, value, callback) => {
      //定义手机号的正则
      const phone = /^1[3456789][0-9]{9}$/
      if (phone.test(value)) {
        return callback()
      }
      callback(new Error('请填写正确的手机号'))
    }
    //确认密码 判断与password是否相同
    const checkPassword = (rule, value, callback) => {
      if (value !== this.addUSerForm.password)
        return callback(new Error('两次密码不一致'))
      //如果相同则回调函数
      callback()
    }
    return {
      //定义分页查询对象
      queryInfo: {
        query: '',  //用户查询的数据
        pageNum: 1, //默认第一页
        pageSize: 5 //每页5条数据
      },
      userList: [], //获取分页后的结果
      total: 0,      //总数
      addUserDialogVisible: false,

      //设定新增用户的对象
      addUSerForm: {
        username: '',
        password: '',
        password2: '',
        phone: '',
        email: ''
      },
      //定义修改用户对话框属性
      updateDialogVisible: false,
      updateUserForm: {},
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        password2: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' },
          { validator: checkPassword, trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入电话', trigger: 'blur' },
          { min: 11, max: 11, message: '长度必须为11位数', trigger: 'blur' },
          { validator: checkPhone, trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 30 个字符', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    //1.动态获取userList数据
    async getUserList() {
      const { data: result } = await this.$http.get('/user/list', {
        params:
          this.queryInfo
      })
      if (result.status !== 200) return this.$message.error("获取列表失败")
      //为total属性赋值
      this.total = result.data.total
      this.userList = result.data.rows
    },
    //页面条数变化的时候触发
    handleSizeChange(pageSize) {
      //查询的条件需要变化
      this.queryInfo.pageSize = pageSize
      //重新查询数据
      this.getUserList()
    },
    //当页面改变的时候触发
    handleCurrentChange(pageNum) {
      this.queryInfo.pageNum = pageNum
      this.getUserList()
    },
    async updateStatus(user) {
      const { data: result } = await this.$http.put(`/user/status/${user.id}/${user.status}`)
      if (result.status !== 200)
        return this.$message.error('状态更改失败')
      this.$message.success('状态更新操作成功')
    },
    closeAddUserDialog() {
      //当对话框关闭时,应该重置表单
      this.$refs.addUserRuleForm.resetFields()
    },
    async addUserBtn() {
      //对整个表单进行校验，获取表单对象，进行数据的校验
      this.$refs.addUserRuleForm.validate(async valid => {
        if (!valid) return this.$message.error('请正确填写')
        //之后发起ajax请求实现用户新增
        const { data: result } = await this.$http.post('/user/addUser', this.addUSerForm)
        if (result.status !== 200) return this.$message.error('新增用户失败')
        this.$message.success('新增用户成功')
        //对话框关闭
        this.addUserDialogVisible = false
        //重新刷新页面
        this.getUserList()
      })
    },
    async deleteUser(user) {
      //定义消息确认框  promise对象
      const result = await this.$confirm('是否永久删除用户' + user.username + '是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(error => error)
      //confirm确认 cancel取消
      if (result !== 'confirm') return this.$message.info("用户取消操作")
      //发起ajax请求实现数据的删除
      const { data: resultDB } = await this.$http.delete('/user/' + user.id)
      if (resultDB.status !== 200) return this.$message.error('用户删除失败')
      this.$message.success('用户删除成功')
      //重新加载用户列表
      this.getUserList()

    },
    updateUserBtn(user) {
      this.updateUserForm = user
      this.updateDialogVisible = true
    },
    async updateUser(){
      const {data: result} = await this.$http.put('/user/updateUser', this.updateUserForm)
      if(result.status !== 200) return this.$message.error('更新用户失败')
      this.$message.success('更新用户成功')
      //重新加载用户列表
      this.getUserList()
      this.updateDialogVisible = false
    }
  },
  //当页面加载完成之后调用该函数
  mounted() {
    //获取userList列表数据
    this.getUserList()
  }
}
</script>


<style lang="less" scoped></style>
