<template>
  <div class="label">
    <h1 style="margin-top: 35px">将晨の站</h1>
    <div>
      <el-input placeholder="Email Address" class="input" v-model="email"></el-input>
      <el-input type="password" placeholder="Password" v-model="password" class="input" ></el-input>
      <el-button class="input" type="primary" @click="login">Login</el-button>
      <div style="font-size: 14px; float: left;margin-left: 43px">
      Oh, <el-button type="text">I forget my password</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
      name: "login",
      data() {
        return {
          password:"",
          email:"",

        }
      },
      methods:{
        login(){
          if(this.email == ""){
            this.info(2,"邮箱不能为空")
            return
          }
          if(this.password == ""){
            this.info(2, "密码不能为空")
            return
          }
          {

            this.$http.get('http://localhost:80/user/login',{
              params: {
                email: this.email,
                password: this.password
              }
            }).then(result=>{
              let response = result.data;
              if(response)
                this.info(1, "登陆成功")
              else
                this.info(3, "登陆失败")
            })
/*
            axios.post('/user/login',{

            }).then(function (response) {
             // alert(response)
              console.log(response)

            })
         //   console.log('test')*/
          }

        },

        info(type, content){
          if(type === 0){   //normal info
            this.$message(content)
          }else if(type === 1){
            this.$message({
              message: content,
              type : 'success'
            })
          }else if(type === 2){
            this.$message({
              message: content,
              type: 'warning'
            });
          }else if(type === 3){
            this.$message.error(content);
          }
        }
      }
  }
</script>

<style scoped>
.label{
  background-color: #ffffff;
  width: 350px;
  height: 400px;
  border-radius: 5px;
}
  .input{
    width: 270px;
    height: 40px;
    margin-top: 20px;
  }
</style>
