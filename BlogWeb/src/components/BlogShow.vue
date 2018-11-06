<template>
  <div>
    <div class="label">
      <mavon-editor style="margin: 40px" v-html="html" :toolbarsFlag="false" :boxShadow="false" :subfield="false" :defaultOpen="defaultData"></mavon-editor>
      <el-button @click="openFile">open</el-button>
      <input @change="fileChange" type="file" id="openFile" multiple style="display: none"/>
    </div>
  </div>

</template>

<script>

  export default {
    name: "blog-show",
    data(){
      return{
        html:"ddd",
        defaultData: "preview"
      }
    },
    created(){
    },
    methods:{

      mdToHtml(md){
        var mymark = require('marked')
          mymark.setOptions({
          baseUrl: null,
          breaks: false,
          gfm: true,
          headerIds: true,
          headerPrefix: "",
          mangle: true,
          pedantic: false,
          sanitize: false,
          sanitizer: null,
          silent: false,
          smartLists: false,
          smartypants: false,
          tables: true,
          xhtml: false,
        })

        this.html = mymark(md)//converter.makeHtml(context)
        console.log(this.html)
      },

      openFile () {
        document.getElementById('openFile').click()
      },

      fileChange (el) {
        if (!el.target.files[0].size) return
        let reader = new FileReader()
        reader.readAsText(el.target.files[0])
        var _this = this
        reader.onload = function () {
          var context = this.result
          _this.mdToHtml(context)
          console.log(context)
          if (context.length === 0) {
            return
          }

        }
        el.target.value = ''
      },

      getArticle(){

      }
    }
  }
</script>

<style>
  .label{
    border-radius: 5px;
    position: absolute;
    top: 130px;
    width: 800px;
    margin-left: 100px;
    background-color: white;
    padding-bottom:100px
  }

</style>
