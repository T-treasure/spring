<template>
  <div>
    <!-- 定义面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品新增</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 定义卡片视图 -->
    <el-card class="box-card">

      <!-- 定义页面提示信息 -->
      <el-alert title="新增商品流程" type="info" effect="dark" center show-icon :closable="false"></el-alert>

      <!-- 定义步骤条 字符串需要转化为数值 -->
      <el-steps :active="activeIndex - 0" finish-status="success" align-center>
        <el-step title="基本信息"></el-step>
        <el-step title="商品参数"></el-step>
        <el-step title="商品属性"></el-step>
        <el-step title="商品图片"></el-step>
        <el-step title="商品内容"></el-step>
        <el-step title="完成"></el-step>
      </el-steps>


      <!-- 定义标签页 before-leave:切换标签之前的钩子，若返回 false 或者返回 Promise 且被 reject，则阻止切换。 -->
      <el-form :model="addItemForm" :rules="addItemFormRules" ref="addItemFormRef" label-width="100px"
        label-position="top">
        <el-tabs v-model="activeIndex" :tab-position="'left'" :before-leave="beforeLeave" @tab-click="tabClick">
          <el-tab-pane label="基本信息" name="0">
            <el-form-item label="商品标题" prop="title">
              <el-input v-model="addItemForm.title"></el-input>
            </el-form-item>
            <el-form-item label="商品卖点" prop="sellPoint">
              <el-input v-model="addItemForm.sellPoint"></el-input>
            </el-form-item>
            <el-form-item label="商品价格" prop="price">
              <el-input v-model="addItemForm.price" type="number"></el-input>
            </el-form-item>
            <el-form-item label="商品数量" prop="num">
              <el-input v-model="addItemForm.num" type="number"></el-input>
            </el-form-item>
            <el-form-item label="商品分类信息" prop="price">
              <!-- 通过级联获取商品分类信息-->
              <el-cascader v-model="itemCatIds" :options="itemCatList" :props="props" @change="changeCascader">
              </el-cascader>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="商品参数" name="1">
            <el-form-item :label="item.paramName" v-for="item in dynamicTableData" :key="item.paramId">
              <!-- 通过复选框展现商品分类参数信息-->
              <el-checkbox-group v-model="item.paramVals">
                <el-checkbox :label="param" v-for="(param,index) in item.paramVals" :key="index" border></el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-tab-pane>

          <!-- 渲染商品分类静态属性数据 -->
          <el-tab-pane label="商品属性" name="2">
            <el-form-item :label="item.paramName" v-for="item in staticTableData" :key="item.paramId">
              <el-input v-model="item.paramVals"></el-input>
            </el-form-item>
          </el-tab-pane>

          <!-- 实现图片上传 multiple支持多选文件 -->
          <el-tab-pane label="商品图片" name="3">
            <!--
              class="upload-demo": 图片上传的类型
              :action="uploadUrl": 必选参数  上传的地址
              :on-preview="handlePreview": 当上传图片预选回调
              :on-remove="handleRemove": 删除图片的回调函数
              :on-success="handleSuccess": 上传成功的回调
              list-type="picture": 上传文件的类型  只允许上传图片
              multiple:  是否支持多选文件
              drag: 是否支持拖拽
              请求的参数名称一定叫: file
            -->
            <el-upload class="upload-demo" :action="uploadUrl" :on-preview="handlePreview" :on-remove="handleRemove"
              :on-success="handleSuccess" list-type="picture" multiple drag>
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
          </el-tab-pane>
          <el-tab-pane label="商品详情" name="4">
            <!-- 定义富文本编辑器-->
            <quill-editor ref="myQuillEditor" v-model="itemDesc.itemDesc">
            </quill-editor>

            <!-- 定义添加商品按钮-->
            <el-button type="primary" class="addItemBtnClass" @click="addItemBtn">添加商品</el-button>
          </el-tab-pane>
        </el-tabs>
      </el-form>

    </el-card>

    <!-- 定义图片展现对话框 -->
    <el-dialog title="图片预览" :visible.sync="dialogImageVisible">
      <img :src="imageUrlPath" width="100%" height="100%" />
    </el-dialog>


  </div>
</template>

<script>
  export default {
    data() {
      return {
        activeIndex: '0',
        addItemForm: {
          title: '',
          sellPoint: '',
          price: 0,
          num: 0,
          itemCatId: '',
          images: []
          //itemDesc: '',
          //dynamicArgs: [],
          //staticArgs: []
        },
        itemDesc: {
          itemDesc: ""
        },
        itemParam: {
          dynamicArray: [],
          staticArray: [],
          dynamicArgs: "",
          staticArgs: ""
        }
        ,
        addItemFormRules: {
          title: [{
            required: true,
            message: '请输入商品标题信息',
            trigger: 'blur'
          }],
          sellPoint: [{
            required: true,
            message: '请输入商品卖点信息',
            trigger: 'blur'
          }],
          price: [{
            required: true,
            message: '请输入商品价格信息',
            trigger: 'blur'
          }],
          num: [{
            required: true,
            message: '请输入商品数量信息',
            trigger: 'blur'
          }],

        },
        itemCatIds: [],
        itemCatList: [],
        props: {
          expandTrigger: 'hover',
          value: "id", //选中数据的value值
          label: "name", //选中数据展现名称
          children: "children", //自选项数据
        },
        //设置商品动态参数
        dynamicTableData: [],
        staticTableData: [],

        //定义文件上传路径地址
        uploadUrl: "http://localhost:8091/file/upload",
        //uploadUrl: "http://manage.harrylyj.com/file/upload",
        //定义图片网络访问地址
        imageUrlPath: "",
        //定义图片控制开关
        dialogImageVisible: false
      }
    },
    created() {
      this.findItemCatList()
    },
    methods: {
      //1.获取所有商品分类信息
      async findItemCatList() {
        const {
          data: result } = await this.$http.get("/itemCat/findItemCatList/3")
        if (result.status !== 200) return this.$message.error("查询商品分类信息失败")
        this.itemCatList = result.data
      },
      //修改商品分类选项
      changeCascader() {
        this.addItemForm.itemCatId = this.itemCatIds[2]
        //console.log(this.addItemForm.itemCatId)
      },
      //当标签页数据没有完成选择时 不让切换标签页
      beforeLeave(activeName, oldActiveName) {
        //console.log(this.itemCatIds.length)
        //console.log(typeof(oldActiveName))
        //注意oldActiveName的数据类型为字符串
        if (this.itemCatIds.length !== 3 && oldActiveName === '0') {
          this.$message.error("请先选择商品分类")
          return false
        }
      },
      async tabClick() {
        //1.判断索引下标值是否为1 商品参数
        if (this.activeIndex === '1') {
          //发起Ajax请求 获取商品分类的参数信息
          //http://localhost:8091/itemCatParam/findItemCatParamListByType?paramType=1&itemCatId=3
          let itemCatId = this.addItemForm.itemCatId
          //动态属性
          let type = 1
          let {
            data: result
          } = await this.$http.get("/itemCatParam/findItemCatParamListByType", {
            params: {
              paramType: type,
              itemCatId: itemCatId
            }
          })
          if (result.status !== 200) return this.$message.error("商品分类参数查询失败")
          //查询完成之后赋值给动态参数对象

          //将商品分类参数中的vals转化为数组
          for (let itemCatParam of result.data) {
            itemCatParam.paramVals = itemCatParam.paramVals ? itemCatParam.paramVals.split(",") : []
          }
          this.dynamicTableData = result.data
        }

        if (this.activeIndex === '2') {
          let itemCatId = this.addItemForm.itemCatId
          //静态属性
          let type = 2
          let {
            data: result
          } = await this.$http.get("/itemCatParam/findItemCatParamListByType", {
            params: {
              paramType: type,
              itemCatId: itemCatId
            }
          })
          if (result.status !== 200) return this.$message.error("商品分类参数查询失败")
          this.staticTableData = result.data
        }
      },
      //预览图片的方法
      handlePreview(file) {
        //获取图片的虚拟路径
        this.imageUrlPath = file.response.data.urlPath
        this.dialogImageVisible = true

      },
      //移除图片的方法
      async handleRemove(file) {
        //移除数组中的数据
        let virtualPath = file.response.data.virtualPath
        //通过findIndex函数 获取数组中指定数据的位置
        let index = this.addItemForm.images.findIndex(x => x === virtualPath)
        //删除数组中指定的数据
        this.addItemForm.images.splice(index, 1)
        //删除服务中的文件
        let {
          data: result
        } = await this.$http.delete("/file/deleteFile", {
          params: {
            virtualPath: virtualPath
          }
        })
        if (result.status !== 200) return this.$message.error("删除图片失败")
        this.$message.success("删除图片成功")
      },
      //如果文件上传成功之后调用
      handleSuccess(response, file) {
        if (response.status !== 200) return this.$message.error("文件上传失败")
        file.name = response.data.fileName
        //获取虚拟路径
        let virtualPath = response.data.virtualPath
        //将数据封装到Form表单中
        this.addItemForm.images.push(virtualPath)
      },

      /* 添加商品按钮 */
      async addItemBtn(){
        //console.log(this.addItemForm)

        //1.完成表单校验
        this.$refs.addItemFormRef.validate( valid => {
          if(!valid) return this.$message.error("请输入商品必填项")
        })

        //2.完成商品参数的封装
        //2.0 将商品价格扩大100倍
        this.addItemForm.price = this.addItemForm.price * 100
        //2.1 将商品图片的数据转化为字符串
        this.addItemForm.images = this.addItemForm.images.join(",")
        //2.2 将商品参数封转为对象 {paramId: vals}
        this.dynamicTableData.forEach(item => {
          let dynamicArg = {paramId : item.paramId, paramVals: item.paramVals.join(",")}
          this.itemParam.dynamicArray.push(dynamicArg)
        })

        //2.3 将商品静态属性封装为对象
        this.staticTableData.forEach(item => {
          let staticArg = {paramId:item.paramId,paramVals:item.paramVals}
          this.itemParam.staticArray.push(staticArg)
        })

        //2.5 实现商品数据提交
        let submitAddItem = {
          item : this.addItemForm,
          itemDesc: this.itemDesc,
          itemParam: this.itemParam
        }


        console.log(submitAddItem)
        let {data: result} = await this.$http.post("/item/saveItem",submitAddItem)
        if(result.status !== 200) return this.$message.error("商品添加失败")
        this.$message.success("商品添加成功")

        //2.5添加完成之后,将数据重定向到商品展现页面
        this.$router.push("/item")
      }
    }
  }
</script>

<style lang="less" scoped>
  .el-steps {
    margin: 20px 0;
  }

  .el-cascader {
    width: 30%;
  }

  /* 控制复选框的右边距5像素 !important 表示优先执行 IE6不兼容*/
  .el-checkbox {
    margin: 0 10px 0 0 !important;
  }

  .addItemBtnClass{

    margin-top: 15px;
  }

</style>
