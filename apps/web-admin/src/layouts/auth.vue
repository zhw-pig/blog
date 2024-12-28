<script lang="ts" setup>
import { computed } from 'vue';

import { AuthPageLayout } from '@zhw/layouts';
import { preferences } from '@zhw/preferences';

const appName = computed(() => preferences.app.name);
const logo = computed(() => preferences.logo.source);
// 登录背景图
const sloganImage = computed(() => '');
</script>

<template>
  <AuthPageLayout
    :app-name="appName"
    :logo="logo"
    :slogan-image="sloganImage"
  />
</template>
