import type {
  BaseFormComponentType,
  FormCommonConfig,
  zhwFormAdapterOptions,
} from './types';

import type { Component } from 'vue';
import { h } from 'vue';

import {
  ZhwButton,
  ZhwCheckbox,
  Input as ZhwInput,
  ZhwInputPassword,
  ZhwPinInput,
  ZhwSelect,
} from '@zhw-core/shadcn-ui';
import { globalShareState } from '@zhw-core/shared/global-state';

import { defineRule } from 'vee-validate';

const DEFAULT_MODEL_PROP_NAME = 'modelValue';

export const DEFAULT_FORM_COMMON_CONFIG: FormCommonConfig = {};

export const COMPONENT_MAP: Record<BaseFormComponentType, Component> = {
  DefaultButton: h(ZhwButton, { size: 'sm', variant: 'outline' }),
  PrimaryButton: h(ZhwButton, { size: 'sm', variant: 'default' }),
  ZhwCheckbox,
  ZhwInput,
  ZhwInputPassword,
  ZhwPinInput,
  ZhwSelect,
};

export const COMPONENT_BIND_EVENT_MAP: Partial<
  Record<BaseFormComponentType, string>
> = {
  ZhwCheckbox: 'checked',
};

export function setupZhwForm<
  T extends BaseFormComponentType = BaseFormComponentType,
>(options: zhwFormAdapterOptions<T>) {
  const { config, defineRules } = options;

  const {
    disabledOnChangeListener = true,
    disabledOnInputListener = true,
    emptyStateValue = undefined,
  } = (config || {}) as FormCommonConfig;

  Object.assign(DEFAULT_FORM_COMMON_CONFIG, {
    disabledOnChangeListener,
    disabledOnInputListener,
    emptyStateValue,
  });

  if (defineRules) {
    for (const key of Object.keys(defineRules)) {
      defineRule(key, defineRules[key as never]);
    }
  }

  const baseModelPropName =
    config?.baseModelPropName ?? DEFAULT_MODEL_PROP_NAME;
  const modelPropNameMap = config?.modelPropNameMap as
    | Record<BaseFormComponentType, string>
    | undefined;

  const components = globalShareState.getComponents();

  for (const component of Object.keys(components)) {
    const key = component as BaseFormComponentType;
    COMPONENT_MAP[key] = components[component as never];

    if (baseModelPropName !== DEFAULT_MODEL_PROP_NAME) {
      COMPONENT_BIND_EVENT_MAP[key] = baseModelPropName;
    }

    // 覆盖特殊组件的modelPropName
    if (modelPropNameMap && modelPropNameMap[key]) {
      COMPONENT_BIND_EVENT_MAP[key] = modelPropNameMap[key];
    }
  }
}
