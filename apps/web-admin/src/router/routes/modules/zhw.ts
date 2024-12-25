import type { RouteRecordRaw } from 'vue-router';

import {
  ZHW_ANT_PREVIEW_URL,
  ZHW_DOC_URL,
  ZHW_GITHUB_URL,
  ZHW_LOGO_URL,
  ZHW_NAIVE_PREVIEW_URL,
} from '@zhw/constants';
import { SvgAntdvLogoIcon } from '@zhw/icons';

import { BasicLayout, IFrameView } from '#/layouts';
import { $t } from '#/locales';

const routes: RouteRecordRaw[] = [
  {
    component: BasicLayout,
    meta: {
      badgeType: 'dot',
      icon: ZHW_LOGO_URL,
      order: 9999,
      title: $t('demos.zhw.title'),
    },
    name: 'ZhwProject',
    path: '/zhw-blog',
    children: [
      {
        name: 'zhwAbout',
        path: '/zhw-blog/about',
        component: () => import('#/views/_core/about/index.vue'),
        meta: {
          icon: 'lucide:copyright',
          title: $t('demos.zhw.about'),
        },
      },
      {
        name: 'ZhwDocument',
        path: '/zhw-blog/document',
        component: IFrameView,
        meta: {
          icon: 'lucide:book-open-text',
          link: ZHW_DOC_URL,
          title: $t('demos.zhw.document'),
        },
      },
      {
        name: 'ZhwGithub',
        path: '/zhw-blog/github',
        component: IFrameView,
        meta: {
          icon: 'mdi:github',
          link: ZHW_GITHUB_URL,
          title: 'Github',
        },
      },
      {
        name: 'ZhwNaive',
        path: '/zhw-blog/naive',
        component: IFrameView,
        meta: {
          badgeType: 'dot',
          icon: 'logos:naiveui',
          link: ZHW_NAIVE_PREVIEW_URL,
          title: $t('demos.zhw.naive-ui'),
        },
      },
      {
        name: 'ZhwAntd',
        path: '/zhw-blog/antd',
        component: IFrameView,
        meta: {
          badgeType: 'dot',
          icon: SvgAntdvLogoIcon,
          link: ZHW_ANT_PREVIEW_URL,
          title: $t('demos.zhw.antdv'),
        },
      },
    ],
  },
];

export default routes;
