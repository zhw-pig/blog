<script lang="ts" setup>
import { AuthenticationQrCodeLogin } from '@zhw/common-ui';
import { LOGIN_PATH } from '@zhw/constants';

defineOptions({ name: 'QrCodeLogin' });
</script>

<template>
  <AuthenticationQrCodeLogin :login-path="LOGIN_PATH" />
</template>
