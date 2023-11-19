<template>
    <v-container>
      <v-form>
        <v-row class="mb-5">
          <v-col class="text-h3">
            공보기 로그인
          </v-col>
        </v-row>
        <v-row dense>
          <v-col>
            <v-text-field type="text" variant="outlined" label="아이디" />
          </v-col>
        </v-row>
        <v-row dense>
          <v-col>
            <v-text-field type="password" variant="outlined" label="패스워드" />
          </v-col>
        </v-row>
        <v-row dense>
          <v-col>
            <v-btn block type="submit" variant="outlined" color="primary">로그인</v-btn>
          </v-col>
        </v-row>
        <v-row dense>
          <v-col class="text-right text-caption">
            처음 방문하시나요? <RouterLink :to="getPath(Names.Join)">회원가입</RouterLink>
          </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-btn outlined>구글로 로그인 버튼 위치</v-btn>
            </v-col>
        </v-row>
      </v-form>
    </v-container>
  </template>
  
  <script setup>
  import { Names, getPath } from '../../router'
  </script>