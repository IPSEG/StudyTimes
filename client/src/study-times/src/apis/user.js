export function fetchUserInformation() {
  return Promise.resolve({
    email: 'test@email.com',
    oauth: {
      google: {
        use: Math.floor(Math.random() * 10) % 2 == 0,
      },
      kakao: {
        use: false,
      },
      naver: {
        use: false,
      }
    },
    timers: {
      use: 10
    }
  })
}