<template>
  <div style="position: absolute; left: 0; top: 0; width: 100%; height: 550px; background-color: #2992e5">
    <login style="position: absolute; left: 200px; top: 100px;"></login>
  </div>

</template>

<script>
  import Login from "../components/Login"
  export default {
    components: {Login},
    name: "login-page",

    data(){
      return{
        imgsrc: "",
      }
    }

  }
</script>

<style scoped>

</style>
