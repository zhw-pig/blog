<script lang="ts" setup>
import { ZhwSpinner } from '@zhw-core/shadcn-ui';

import { useContentSpinner } from './use-content-spinner';

defineOptions({ name: 'LayoutContentSpinner' });

const { spinning } = useContentSpinner();
</script>
<template>
  <ZhwSpinner :spinning="spinning" />
</template>
