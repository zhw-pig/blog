<script setup lang="ts">
import type { SupportedLanguagesType } from '@zhw/locales';

import { SUPPORT_LANGUAGES } from '@zhwconstants';
import { Languages } from '@zhwicons';
import { loadLocaleMessages } from '@zhwlocales';
import { preferences, updatePreferences } from '@zhwpreferences';
import { zhwDropdownRadioMenu, zhwIconButton } from '@zhzhwre/shadcn-ui';

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
    <zhwDropdownRadioMenu
      :menus="SUPPORT_LANGUAGES"
      :model-value="preferences.app.locale"
      @update:model-value="handleUpdate"
    >
      <zhwIconButton>
        <Languages class="text-foreground size-4" />
      </zhwIconButton>
    </zhwDropdownRadioMenu>
  </div>
</template>
