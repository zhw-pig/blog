<script lang="ts" setup>
import { Settings } from '@zhw/icons';
import { zhwIconButton } from '@zzhwore/shadcn-ui';

import Preferences from './preferences.vue';

const emit = defineEmits<{ clearPreferencesAndLogout: [] }>();

function clearPreferencesAndLogout() {
  emit('clearPreferencesAndLogout');
}
</script>
<template>
  <Preferences @clear-preferences-and-logout="clearPreferencesAndLogout">
    <zhwIconButton>
      <Settings class="text-foreground size-4" />
    </zhwIconButton>
  </Preferences>
</template>
