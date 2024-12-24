<script setup lang="ts">
import type { SupportedLanguagesType } from '@zhw/locales';

import { SUPPORT_LANGUAGES } from '@zhw/constants';
import { Languages } from '@zhw/icons';
import { loadLocaleMessages } from '@zhw/locales';
import { preferences, updatePreferences } from '@zhw/preferences';
import { ZhwDropdownRadioMenu, ZhwIconButton } from '@zhw-core/shadcn-ui';

defineOptions({
  name: 'LanguageToggle',
});

async function handleUpdate(value: string) {
  const locale = value as SupportedLanguagesType;
  updatePreferences({
    app: {
      locale,
    },
  });
  await loadLocaleMessages(locale);
}
</script>

<template>
  <div>
    <ZhwDropdownRadioMenu
      :menus="SUPPORT_LANGUAGES"
      :model-value="preferences.app.locale"
      @update:model-value="handleUpdate"
    >
      <ZhwIconButton>
        <Languages class="text-foreground size-4" />
      </ZhwIconButton>
    </ZhwDropdownRadioMenu>
  </div>
</template>
