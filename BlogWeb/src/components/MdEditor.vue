<template>
  <div>
    <div style="position: fixed; left: 0;top:0;width: 100%; height: 100px; background-color: #353535">
      <el-button size="small" type="text" class="back">
        <i style="font-weight: bold;font-size: 20px" class="el-icon-arrow-left"></i>个人中心
      </el-button>
      <el-button @click="dialogVisible=true" size="small" type="primary" class="submit">发布文章</el-button>
    </div>
    <mavon-editor ref=md @imgAdd="$imgAdd" @imgDel="$imgDel" class="myDiv" :ishljs="true" v-model="mdValue"></mavon-editor>

    <el-dialog class="dialog"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <div style="height: 220px;border-radius: 5px">
        <span style="position: absolute; left: 30px; top: 20px;font-size: 16px;font-weight: bold">发布文章</span>
        <span style="position: absolute; left: 30px; top: 70px;font-size: 16px;">文章标题</span>
        <el-input style="position: absolute; width: 300px; left: 120px; top: 65px;" size="small"></el-input>
        <div>
          <span style="position: absolute; left: 30px; top: 120px;font-size: 16px;" >添加标签</span>
          <el-input v-model="tag"  style="position: absolute; left: 120px; top: 115px;width: 90px" size="small"></el-input>
          <el-button @click="addTag" style="position: absolute; left: 220px; top: 112px;font-size: 18px" type="text" size="medium" icon="el-icon-circle-plus-outline">
          </el-button>
          <span style="position: absolute; left: 242px; top: 123px;font-size: 12px">最多五个标签</span>
          <div style="position: absolute;top:160px;left: 120px;width: 400px;">
            <div  v-for="item2 in tagList">
              <div class="item2">
                <span>{{item2}}</span>
                <el-button class="removeBtn" icon="el-icon-close" type="text" @click="remove(item2)"
                           style="color: white; font-size: 11px; position: absolute; right: 0px;top: -12px;"></el-button>
              </div>
            </div>
          </div>
        </div>
        <div>
          <span style="position: absolute; left: 30px; top: 195px;font-size: 16px;">选择类别</span>
          <div>
            <el-select style="position: absolute; left: 120px;top:190px;width: 300px" v-model="category" multiple placeholder="请选择" size="small">
              <el-option
                v-for="item in option"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </div>
        </div>
      </div>
      <div>
        <el-button style="position: absolute;right: 150px;bottom: 15px" class="diaBtn" type="text" size="small" @click="dialogVisible = false">取 消</el-button>
        <el-button style="position: absolute;right: 80px;bottom: 15px" class="diaBtn sureBtn" size="small" type="text" @click="dialogVisible = false">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "md-editor",
    props: {
      tagList: {
        type: Array,
        default() {
          return [];
        }
      }
    },
    data(){
      return{
        mdValue:"",
        dialogVisible:false,
        img_file:{},
        category:"",
        option:[
          {
            value:"technology",
            label:"技术"
          },
          {
            value:"learning",
            label:"学习"
          },
          {
            value:"others",
            label:"随笔"
          },
          {
            value:"sharing",
            label:"分享"
          }
        ],
        tag:"",
        maxNumOfLabels:5
      }
    },
    methods:{
      addTag(){
        if(this.tagList.length>=this.maxNumOfLabels) {
          return;
        }
        var has = false;
        var item = this.trim(this.tag)
        if(item == '') return;

        for(var i=0;i<this.tagList.length;i++){
          if(this.tagList[i]==item){
            has=true;
          }
        }
        if(has == false&&this.tagList.length<=this.maxNumOfLabels)
          this.tagList.push(item);
        this.tag = ""
      },

      remove(item){
        var index = 0;
        for(var i=0;i<this.tagList.length;i++){
          if(this.tagList[i]==item){
            index = i;
            break;
          }
        }
        this.tagList.splice(index, 1)
      },

      trim(str){
          var str1 = str.replace(/^\s*/g, '')
          return str1.replace(/\s*$/g, '')
      },
      submit(){

      },
      // 绑定@imgAdd event
      $imgAdd(pos, $file){
        // 缓存图片信息
        this.img_file[pos] = $file;
      },
      $imgDel(pos){
        delete this.img_file[pos];
      },
      uploadimg($e){
        // 第一步.将图片上传到服务器.
        var formdata = new FormData();
        for(var _img in this.img_file){
          formdata.append(_img, this.img_file[_img]);
        }
        axios({
          url: 'http://localhost:80/blog/img/upload',
          method: 'post',
          data: formdata,
          headers: { 'Content-Type': 'multipart/form-data' },
        }).then((res) => {
          /**
           * 例如：返回数据为 res = [[pos, url], [pos, url]...]
           * pos 为原图片标志（0）
           * url 为上传后图片的url地址
           */
          // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
          for (var img in res) {
            // $vm.$img2Url 详情见本页末尾
            $vm.$img2Url(img[0], img[1]);
          }
        })
      },
    }
  }
</script>

<style scoped>
  .myDiv{
    width: 100%;
    left: 0;
    position: fixed;
    height: 100%;
  }
  .back{
    position: fixed;left: 20px;top: 10px; color: #dedede;font-size: 20px;font-weight: bold;padding-right: 5px;
  }
  .back:hover{
    background-color: #dedede;
    color: #797979;
  }
  .back:active{
    left: 19px;
  }
  .submit{
    position: fixed;
    right: 30px;
    top: 10px;
    font-size: 18px;
  }
  .dialog{
    position: absolute;
    left: 50%;
    margin-left: -800px;
    width: 1600px;
  }
  .item2{
    position: relative;
    font-size: 10px;
    color: white;
    margin: 2px;
    height: 17px;
    background-color: #ff8432;
    border-radius: 5px;
    float: left;
    padding-left: 6px;
    padding-right: 6px;
    padding-top:4px;
  }
  .removeBtn{
    display: none;
  }
  .item2:hover{
    background-color: #ff732f;
    cursor: default
  }
  .item2:hover .removeBtn{
    display: block;
  }
  .diaBtn{
    color: #2c2e30;
    font-size: 14px;
    padding-left: 10px;
    padding-right: 10px;
  }
  .diaBtn:hover{
    background-color: #c3c3c3;
  }
  .sureBtn{
    color: #108eea;
  }
</style>
