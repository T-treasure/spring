<template>
  <div>
    <!-- 定义面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品分类参数</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 定义卡片视图 -->
    <el-card class="box-card">

      <!-- 1.定义头部警告区域 不可以被关闭-->
      <el-alert title="注意: 只允许为三级商品分类设定参数" type="warning" show-icon center :closable="false"></el-alert>

      <!-- 2.定义商品分类选择区域 -->
      <el-row>
        <el-col>
          <p>
            <strong>
              选择商品分类:
              <!-- 定义级联选择器,获取商品分类信息-->
              <!-- 数据说明: options="数据的来源" props:定义级联选择器父子关系信息 -->
              <el-cascader v-model="itemCatIds" :options="itemCatList" :props="props" clearable @change="handleChange">
              </el-cascader>
            </strong>
          </p>
        </el-col>
      </el-row>

      <!-- 定义tabs 标签页 activeName被激活的页签 -->
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <!--定义动态参数的按钮 -->
        <el-tab-pane label="动态参数" name="dynamic">

          <!-- 定义添加参数按钮-->
          <el-button type="primary" size="mini" :disabled="isDisableBtn" @click="addDialogVisible = true">添加动态参数
          </el-button>

          <!-- 动态参数表格-->
          <el-table :data="dynamicTableData" stripe border>
            <el-table-column type="expand">
              <template slot-scope="scope">
                <!-- 循环遍历展现数据-->
                <el-tag v-for="(paramVal,index) in scope.row.paramVals" :key="index" closable @close="closeTag(scope.row,index)">{{paramVal}}</el-tag>

                <!-- 添加新增tag标签框-->
                <el-input class="input-new-tag" v-if="scope.row.inputVisible" v-model="scope.row.inputValue"
                  ref="saveDynamicTagInput" size="small" @keyup.enter.native="handleInputConfirm(scope.row)"
                  @blur="handleInputConfirm(scope.row)">
                </el-input>
                <el-button v-else class="button-new-tag" size="small" @click="showInput(scope.row)">+ New Tag
                </el-button>


              </template>
            </el-table-column>
            <el-table-column type="index" label="序号"></el-table-column>
            <el-table-column prop="paramName" label="参数名称"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="primary" icon="el-icon-edit" size="mini" @click="updateItemCatParamBtn(scope.row)">编辑
                </el-button>
                <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteItemCatParamBtn(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <!--定义静态属性的按钮 -->
        <el-tab-pane label="静态属性" name="static">
          <!-- 定义添加参数按钮-->
          <el-button type="primary" size="mini" :disabled="isDisableBtn" @click="addDialogVisible = true">添加静态属性
          </el-button>

          <!-- 定义静态属性表格-->
          <el-table :data="staticTableData" stripe border>
            <el-table-column type="expand">
              <template slot-scope="scope">
                <!-- 循环遍历展现数据-->
                <el-tag v-for="(paramVal,index) in scope.row.paramVals" :key="index" closable @close="closeTag(scope.row,index)">{{paramVal}}</el-tag>

                <!-- 添加新增tag标签框-->
                <el-input class="input-new-tag" v-if="scope.row.inputVisible" v-model="scope.row.inputValue"
                  ref="saveDynamicTagInput" size="small" @keyup.enter.native="handleInputConfirm(scope.row)"
                  @blur="handleInputConfirm(scope.row)">
                </el-input>
                <el-button v-else class="button-new-tag" size="small" @click="showInput(scope.row)">+ New Tag
                </el-button>
              </template>
            </el-table-column>

            <el-table-column type="index" label="序号"></el-table-column>
            <el-table-column prop="paramName" label="属性名称"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="primary" icon="el-icon-edit" size="mini" @click="updateItemCatParamBtn(scope.row)">编辑
                </el-button>
                <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteItemCatParamBtn(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <!-- 定义新增参数对话框 -->
    <el-dialog :title="'添加' + getTitleName" :visible.sync="addDialogVisible" width="50%" @close="closeDialog">

      <!-- 添加参数对form表单提交 -->
      <el-form :model="addItemCatParam" :rules="itemCatParamRules" ref="addItemCatParamRef" label-width="100px"
        class="demo-ruleForm">
        <el-form-item :label="getTitleName" prop="paramName">
          <el-input v-model="addItemCatParam.paramName"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addItemCatParamBtn">确 定</el-button>
      </span>

    </el-dialog>

    <!-- 定义修改参数对话框 -->
    <el-dialog :title="'修改' + getTitleName" :visible.sync="updateDialogVisible" width="50%" @close="closeUpdateDialog">

      <!-- 修改参数对form表单提交 -->
      <el-form :model="updateItemCatParam" :rules="itemCatParamRules" ref="updateItemCatParamRef" label-width="100px">
        <el-form-item :label="getTitleName" prop="paramName">
          <el-input v-model="updateItemCatParam.paramName"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateItemCatParamSubmit">确 定</el-button>
      </span>

    </el-dialog>




  </div>
</template>

<script>
  export default {
    //1.定义初始化函数
    created() {
      //获取所有的商品分类信息
      this.getItemCatList()
    },
    data() {
      return {
        itemCatList: [],
        //定义级联选择器属性
        props: {
          expandTrigger: 'hover',
          value: "id", //定义选中的值
          label: "name", //定义展现的值
          children: "children" //定义子级信息
        },
        //定义商品分类ID
        itemCatIds: [],
        //默认激活的页面名称
        activeName: "dynamic",
        //定义动态参数
        dynamicTableData: [],
        //定义静态属性
        staticTableData: [],
        //控制对话框显示
        addDialogVisible: false,
        //定义新增商品参数的form对象
        addItemCatParam: {
          //定义商品分类ID
          itemCatId: '',
          paramName: '',
          paramType: 1
        },
        updateItemCatParam: {
          paramId: '',
          paramName: ''
        },
        //定义商品分类参数校验规则
        itemCatParamRules: {
          paramName: [{
            required: true,
            message: '请输参数名称',
            trigger: 'blur'
          }]
        },
        //定义修改对话框的开关
        updateDialogVisible: false
      }
    },
    methods: {
      //获取所有商品分类信息
      async getItemCatList() {
        const {
          data: result
        } = await this.$http.get("/itemCat/findItemCatList/3")
        if (result.status !== 200) return this.$message.error("商品分类查询失败")
        this.itemCatList = result.data
        //console.log(this.itemCatList)
      },
      //当级联选择器发生变化时 触发事件 获取数据
      handleChange() {
        this.getItemCatParam()
      },
      handleClick() {
        //默认点击事件
        this.getItemCatParam()
      },
      async getItemCatParam() {
        //如果商品分类不是3级时,需要直接返回 将数组清空
        if (this.itemCatIds.length !== 3) {
          this.itemCatIds = []
          this.dynamicTableData = []
          this.staticTableData = []
          return
        }
        //将字符串名称转化为 type类型
        let itemCatId = this.itemCatIds[2]
        let paramType = this.activeName === "dynamic" ? 1 : 2

        //根据itemCatId 获取对应的商品分类参数数据
        let {
          data: result
        } = await this.$http.get("/itemCatParam/findItemCatParamListByType", {
          params: {
            itemCatId: itemCatId,
            paramType: paramType
          }
        })
        if (result.status !== 200) return this.$message.error("获取商品分类参数失败")

        //为了展现商品参数tab页 则利用,号分割参数  val1,val2,val3,val4 最好判断数据是否有效
        for (let itemCatParam of result.data) {
          itemCatParam.paramVals = itemCatParam.paramVals ? itemCatParam.paramVals.split(",") : [],
            //设定input框是否显示
            itemCatParam.inputVisible = false
          //定义用户输入的内容
          itemCatParam.inputValue = ''
        }
        //获取数据之后根据参数类型赋值
        if (paramType === 1) {
          this.dynamicTableData = result.data
        } else {
          this.staticTableData = result.data
        }
      },
      //当关闭添加对话框时重置表格数据
      closeDialog() {
        this.$refs.addItemCatParamRef.resetFields()
      },
      //添加商品分类参数信息
      addItemCatParamBtn() {
        //完成用户数据校验
        this.$refs.addItemCatParamRef.validate(async (valid) => {
          if (valid) {
            this.addItemCatParam.itemCatId = this.getItemCatId()
            this.addItemCatParam.paramType = this.getParamType()

            //发起post请求,实现商品分类参数新增
            let {
              data: result
            } = await this.$http.post("/itemCatParam/addItemCatParam", this.addItemCatParam)
            if (result.status !== 200) {
              return this.$message.error("商品分类参数新增失败")
            }
            this.$message.success("商品分类参数提交成功")
            //重新加载商品分类参数列表
            this.getItemCatParam()
            //关闭对话框
            this.addDialogVisible = false
          } else {
            return false;
          }
        })
      },
      //获取商品分类信息
      getItemCatId() {
        if (this.itemCatIds.length === 3) {
          return this.itemCatIds[2]
        }
        return null
      },
      getParamType() {
        return this.activeName === "dynamic" ? 1 : 2
      },
      //点击更新按钮 实现数据封装
      updateItemCatParamBtn(itemCatParam) {
        this.updateItemCatParam.paramId = itemCatParam.paramId
        this.updateItemCatParam.paramName = itemCatParam.paramName
        //展现修改对话框
        this.updateDialogVisible = true
      },
      //实现商品分类参数更新操作
      updateItemCatParamSubmit() {
        //完成用户数据校验
        this.$refs.updateItemCatParamRef.validate(async (valid) => {
          if (valid) {
            //发起put请求,实现商品分类参数新增
            let {
              data: result
            } = await this.$http.put("/itemCatParam/updateItemCatParam", this.updateItemCatParam)
            if (result.status !== 200) {
              return this.$message.error("商品分类参数修改失败")
            }
            this.$message.success("商品分类参数修改成功")
            //重新加载商品分类参数列表
            this.getItemCatParam()
            //关闭对话框
            this.updateDialogVisible = false
          } else {
            return false;
          }
        })
      },
      closeUpdateDialog() {
        this.$refs.updateItemCatParamRef.resetFields()
      },

      //实现商品删除操作
      async deleteItemCatParamBtn(itemCatParam) {
        let confirmResult = await this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(error => error)

        if (confirmResult !== "confirm") return this.$message.info("删除操作取消")
        //请求路径:/itemCatParam/deleteItemCatParamById
        let {
          data: result
        } = await this.$http.delete("/itemCatParam/deleteItemCatParamById", {
          params: {
            paramId: itemCatParam.paramId
          }
        })
        if (result.status !== 200) return this.$message.error("删除操作失败")
        this.$message.success("删除操作成功")
        //重新加载数据
        this.getItemCatParam()
      },
      handleInputConfirm(itemCatParam) {
        //确认用户输入的内容是有效的
        if(itemCatParam.inputValue.trim().length === 0 ){
          //重置数据
          itemCatParam.inputValue = ''
          itemCatParam.inputVisible = false
          //停止程序执行
          return
        }

        //如果用户数据不为null 则进行数据更新操作
        itemCatParam.paramVals.push(itemCatParam.inputValue)

        //更新商品分类参数
        this.updateItemCatParamArgs(itemCatParam)

      },
      showInput(itemCatParam) {
        itemCatParam.inputVisible = true

        //点击input框之后,自动获取焦点 当页面上元素被重新渲染之后 才可以执行
        this.$nextTick(_ => {
          this.$refs.saveDynamicTagInput.$refs.input.focus();
        })
      },
      closeTag(itemCatParam,index){
         //删除数组中指定的数据
         itemCatParam.paramVals.splice(index,1)
         //之后将数据保存即可
         this.updateItemCatParamArgs(itemCatParam)
      },
      async updateItemCatParamArgs(itemCatParam){
        itemCatParam.inputValue = ''
        itemCatParam.inputVisible = false
        let strVals = itemCatParam.paramVals.join(",")
        let paramId = itemCatParam.paramId
        //更新商品分类参数
        let {data: result} = await this.$http.put("/itemCatParam/updateItemCatParam",{paramId:paramId,paramVals:strVals})
        if(result.status !== 200) return this.$message.error("商品分类参数更新失败")
        this.$message.success("商品分类参数更新成功")
      }

    },
    computed: {
      //如果禁用返回true,否则返回false
      isDisableBtn() {
        if (this.itemCatIds.length !== 3)
          return true
        return false
      },
      getTitleName() {
        return this.activeName === "dynamic" ? "动态参数" : "静态属性"
      }
    }
  }
</script>

<style lang="less" scoped>
  .el-cascader {
    width: 30%;
  }

  .input-new-tag {
    width: 200px;
  }
</style>
