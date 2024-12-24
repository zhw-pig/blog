<script lang="ts" setup>
import { Settings } from '@zhw/icons';
import { ZhwIconButton } from '@zhw-core/shadcn-ui';

import Preferences from './preferences.vue';

const emit = defineEmits<{ clearPreferencesAndLogout: [] }>();

function clearPreferencesAndLogout() {
  emit('clearPreferencesAndLogout');
}
</script>
<template>
  <Preferences @clear-preferences-and-logout="clearPreferencesAndLogout">
    <ZhwIconButton>
      <Settings class="text-foreground size-4" />
    </ZhwIconButton>
  </Preferences>
</template>
