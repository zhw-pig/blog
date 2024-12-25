<script lang="ts" setup>
import type { ZhwFormSchema } from '@zhw/common-ui';
import type { Recordable } from '@zhw/types';

import { computed, ref } from 'vue';

import { AuthenticationForgetPassword, z } from '@zhw/common-ui';
import { $t } from '@zhw/locales';

defineOptions({ name: 'ForgetPassword' });

const loading = ref(false);

const formSchema = computed((): ZhwFormSchema[] => {
  return [
    {
      component: 'zhwInput',
      componentProps: {
        placeholder: 'example@example.com',
      },
      fieldName: 'email',
      label: $t('authentication.email'),
      rules: z
        .string()
        .min(1, { message: $t('authentication.emailTip') })
        .email($t('authentication.emailValidErrorTip')),
    },
  ];
});

function handleSubmit(value: Recordable<any>) {
  // eslint-disable-next-line no-console
  console.log('reset email:', value);
}
</script>

<template>
  <AuthenticationForgetPassword
    :form-schema="formSchema"
    :loading="loading"
    @submit="handleSubmit"
  />
</template>
