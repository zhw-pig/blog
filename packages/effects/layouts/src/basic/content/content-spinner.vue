<script lang="ts" setup>
import { zhwSpinner } from '@zhw-core/shadcn-ui';

import { useContentSpinner } from './use-content-spinner';

defineOptions({ name: 'LayoutContentSpinner' });

const { spinning } = useContentSpinner();
</script>
<template>
  <zhwSpinner :spinning="spinning" />
</template>
