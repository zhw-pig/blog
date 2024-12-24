import type {
  BaseFormComponentType,
  ExtendedFormApi,
  ZhwFormProps,
} from './types';

import { defineComponent, h, isReactive, onBeforeUnmount, watch } from 'vue';

import { useStore } from '@zhw-core/shared/store';

import { FormApi } from './form-api';
import ZhwUseForm from './zhw-use-form.vue';

export function useZhwForm<
  T extends BaseFormComponentType = BaseFormComponentType,
>(options: ZhwFormProps<T>) {
  const IS_REACTIVE = isReactive(options);
  const api = new FormApi(options);
  const extendedApi: ExtendedFormApi = api as never;
  extendedApi.useStore = (selector) => {
    return useStore(api.store, selector);
  };

  const Form = defineComponent(
    (props: ZhwFormProps, { attrs, slots }) => {
      onBeforeUnmount(() => {
        api.unmount();
      });
      api.setState({ ...props, ...attrs });
      return () =>
        h(ZhwUseForm, { ...props, ...attrs, formApi: extendedApi }, slots);
    },
    {
      inheritAttrs: false,
      name: 'ZhwUseForm',
    },
  );
  // Add reactivity support
  if (IS_REACTIVE) {
    watch(
      () => options.schema,
      () => {
        api.setState({ schema: options.schema });
      },
      { immediate: true },
    );
  }

  return [Form, extendedApi] as const;
}
