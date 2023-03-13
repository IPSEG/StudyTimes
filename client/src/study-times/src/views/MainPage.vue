<template>
  <div class="mainbody">
    <p class="date">{{ today }}</p>
    <p class="time">{{ time }}</p>
    <v-btn class="timerbtn" variant="flat" color="#707070" @click="createTimer">타이머 만들기</v-btn>  
  </div>
</template>

<script>
import dayjs from 'dayjs';
import 'dayjs/locale/ko';
dayjs.locale('ko');

export default{
  conponents:{
    dayjs,
  },
  data: () => {
    return{
      today: dayjs().format("YYYY년 MM월 DD일 dddd"),
      time: dayjs().format("HH:mm:ss")      
    }
  },
  methods:{
    createTimer: function(){
      console.log('test');
    }
  },
  mounted(){
    setInterval(() => {
      this.today = dayjs().format("YYYY년 MM월 DD일 dddd");
      this.time = dayjs().format("HH:mm:ss");
    }, 1000);
  }
}


</script>

<style scoped>
.mainbody {
  background-color: #B7B7B7;
  width: 100vw;
  height: 100vh;
}
.date{
  padding-top: 100px;
  font-size: 40px;
  font-weight: 600;
}
.time{
  font-size: 50px;
  font-weight: 600;
  margin-top: 50px;
  margin-bottom: 30px
}
.timerbtn{
  width: 200px;
  height: 50px;
  margin-top: 50px;
  margin-bottom: 50px;
  font-size: 20px;
  font-weight: 600;
}


</style>