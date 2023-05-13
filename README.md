[![](https://jitpack.io/v/NightRainDream/EaseDialog.svg)](https://jitpack.io/#NightRainDream/EaseDialog)

# EaseDialog

## 1.介绍
基于原生Dialog封装，自带仿鸿蒙主题Dialog

## 2.安装教程

在`setting.gradle`文件中加入如下代码

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url 'https://jitpack.io' }
        ...
    }
}
```
在`app`目录下的`build.gradle`文件中加入如下代码

```
dependencies {
	implementation 'com.github.NightRainDream:EaseDialog:Tag'
}
```
## 3.使用说明

### 3.1.初始化Module

在Application初始化

```
override fun onCreate() {
   super.onCreate()
   EaseDialog.initialize(this)
}
```
### 3.2.自带样式

#### 3.2.1.提示性质对话框

```kotlin
DialogTools.getDialogBuilder()
    .setTitleText("允许日历联网")
    .setMainText("下载或更新节假日、班休、历法、订阅等信息。")
    .setPositiveTextColor(Color.RED)
    .toTipsDialog(this, object : IDialogActionCallback {
        override fun onPositive(content: String, index: MutableList<Int>) {
            DialogTools.getToastBuilder()
                .toToast("确定")
        }

        override fun onCancel() {
            DialogTools.getToastBuilder()
                .toToast("取消")
        }
    })
```

#### 3.2.2.警告性质对话框

```kotlin
DialogTools.getDialogBuilder()
    .setMainText("确定删除当前联系人确定删除当前联系人确定删除当前联系人?")
    .setPositiveTextColor(Color.RED)
    .setPositiveText("删除")
    .toWarnDialog(this, object : IDialogActionCallback {
        override fun onPositive(content: String, index: MutableList<Int>) {
            DialogTools.getToastBuilder()
                .toToast("确定")
        }

        override fun onCancel() {
            DialogTools.getToastBuilder()
                .toToast("取消")
        }
    })
```

#### 3.2.3.加载对话框

```kotlin
DialogTools.getDialogBuilder()
    .toLoadingDialog(this)
```

#### 3.2.4.单选菜单

```kotlin
DialogTools.getDialogBuilder()
    .setTitleText("重复")
    .toSingleMenu(
        this,
        mSingleList,
        mSelectSingleIndex,
        object : IDialogActionCallback {
            override fun onPositive(content: String, index: MutableList<Int>) {
                mSelectSingleIndex = index.first()
                DialogTools.getToastBuilder()
                    .toToast(content)
            }

            override fun onCancel() {
                super.onCancel()
                DialogTools.getToastBuilder()
                    .toToast("取消")
            }
        })
```

#### 3.2.5.多选菜单

```kotlin
DialogTools.getDialogBuilder()
    .setTitleText("重复")
    .toMultipleMenu(this, mMultipleList, mSelectMultipleIndex,
        object : IDialogActionCallback {
            override fun onPositive(content: String, index: MutableList<Int>) {
                mSelectMultipleIndex.clear()
                mSelectMultipleIndex.addAll(index)
                DialogTools.getToastBuilder()
                    .toToast("确定==>" + index.size)
            }

            override fun onCancel() {
                super.onCancel()
                DialogTools.getToastBuilder()
                    .toToast("取消")
            }
        })
```

#### 3.2.6.日期时间选择器

```kotlin
DialogTools.getPickerBuilder()
    .setTitleText("请选择日期和时间")
    .toDateTimePicker(this,PICKER_ALL, object : IDateTimeCallback {
        override fun onSelectDate(result: EaseDateTimeEntity) {
            DialogTools.getToastBuilder()
                .toToast(result.getDateTime())
        }

        override fun onCancel() {
            super.onCancel()
            DialogTools.getToastBuilder()
                .toToast("取消")
        }
    })
```

#### 3.2.7.地址选择器
```kotlin
DialogTools.getPickerBuilder()
    .setTitleText("请选择地址")
    .setSelectProvince(mProvince)
    .setSelectCity(mCity)
    .setSelectCounty(mCounty)
    .toAddressPicker(this, PICKER_ADDRESS_PROVINCE_CITY, object : ILocationCallback {
        override fun onAddressSelected(
            province: EaseLocationEntity,
            city: EaseLocationEntity,
            county: EaseLocationEntity
        ) {
            mProvince = province
            mCity = city
            mCounty = county
            DialogTools.getToastBuilder().toToast(province.name + city.name + county.name)
        }
    })
```
#### 3.2.8.颜色选择器
```kotlin
DialogTools.getPickerBuilder()
    .setTitleText("请选择颜色")
    .toColorPicker(this, object : IColorSelectCallback {
        override fun onSelected(red: Int, green: Int, blue: Int) {
            DialogTools.getToastBuilder().toToast("R:".plus(red).plus("|G:").plus(green).plus("|B:").plus(blue))
        }
    })
```

#### 3.2.9.地址选择器
```kotlin
DialogTools.getToastBuilder()
    .toToast("文件删除成功~")
```







