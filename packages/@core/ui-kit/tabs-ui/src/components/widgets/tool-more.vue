<script lang="ts" setup>
import type { DropdownMenuProps } from '@zhw-core/shadcn-ui';

import { ChevronDown } from '@zhw-core/icons';
import { ZhwDropdownMenu } from '@zhw-core/shadcn-ui';

defineProps<DropdownMenuProps>();
</script>

<template>
  <ZhwDropdownMenu :menus="menus" :modal="false">
    <div
      class="flex-center hover:bg-muted hover:text-foreground text-muted-foreground border-border h-full cursor-pointer border-l px-2 text-lg font-semibold"
    >
      <ChevronDown class="size-4" />
    </div>
  </ZhwDropdownMenu>
</template>
