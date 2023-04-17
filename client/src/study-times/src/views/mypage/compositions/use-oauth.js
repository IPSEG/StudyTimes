import { computed } from 'vue'

export default (oauth) => {
  const onClickGoogle = () => { }
  const onClickNaver = () => { }
  const onClickKakao = () => { }

  const menu = computed(() => [
    {
      title: `구글 로그인 연결 ${oauth.value?.google?.use ? '됨' : ''}`,
      value: 'google',
      icon: 'mdi-clock',
      click: onClickGoogle
    },
    {
      title: `네이버 로그인 연결 ${oauth.value?.naver?.use ? '됨' : ''}`,
      value: 'naver',
      icon: 'mdi-clock',
      click: onClickNaver
    },
    {
      title: `카카오 로그인 연결 ${oauth.value?.kakao?.use ? '됨' : ''}`,
      value: 'kakao',
      icon: 'mdi-clock',
      click: onClickKakao
    }
  ])

  return {
    menu
  }
}