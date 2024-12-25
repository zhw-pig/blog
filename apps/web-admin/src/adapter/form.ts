import type { zhwFormSchema as FormSchema, zhwFormProps } from '@zhw/common-ui';

import type { ComponentType } from './component';

import { setupZhwForm, useZhwForm as useForm, z } from '@zhw/common-ui';
import { $t } from '@zhw/locales';

setupZhwForm<ComponentType>({
  config: {
    modelPropNameMap: {
      Upload: 'fileList',
      CheckboxGroup: 'model-value',
    },
  },
  defineRules: {
    required: (value, _params, ctx) => {
      if (value === undefined || value === null || value.length === 0) {
        return $t('ui.formRules.required', [ctx.label]);
      }
      return true;
    },
    selectRequired: (value, _params, ctx) => {
      if (value === undefined || value === null) {
        return $t('ui.formRules.selectRequired', [ctx.label]);
      }
      return true;
    },
  },
});

const useZhwForm = useForm<ComponentType>;

export { useZhwForm, z };

export type zhwFormSchema = FormSchema<ComponentType>;
export type { zhwFormProps };
