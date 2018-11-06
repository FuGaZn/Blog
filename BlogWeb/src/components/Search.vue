<template>
  <div>
    <el-input style="width: 170px" size="small" v-model="content" placeholder="站内搜索"></el-input>
    <el-button type="primary" size="small">查找</el-button>
  </div>
</template>

<script>
  export default {
    name: "search",
    data(){
      return{
        content:""
      }
    }
  }
</script>

<style scoped>

</style>
