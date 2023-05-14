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
	implementation 'com.github.NightRainDream:EaseDialog:VERSION'
}
```
## 3.使用说明
### 3.1.初始化Module
在Application初始化
```kotlin
override fun onCreate() {
   super.onCreate()
   EaseDialog.initialize(this)
}
```
### 3.2.普通对话框
#### 3.2.1.提示性质对话框
```kotlin
DialogTools.getDialogBuilder()
    .setTitleText("允许日历联网")
    .setMainText("下载或更新节假日、班休、历法、订阅等信息。")
    .setPositiveTextColor(Color.RED)
    .toTipsDialog(this, object : ITipsModalCallback {
        override fun onPositive() {
            DialogTools.getToastBuilder().toToast("点击确定")
        }

        override fun onCancel() {
            DialogTools.getToastBuilder().toToast("点击取消")
        }
    })
```

#### 3.2.2.警告性质对话框
```kotlin
DialogTools.getDialogBuilder()
    .setMainText("确定删除当前联系人?")
    .setPositiveTextColor(Color.RED)
    .setPositiveText("删除")
    .toWarnDialog(this, object : IWarnModalCallback {
        override fun onPositive() {
            DialogTools.getToastBuilder().toToast("点击确定")
        }

        override fun onCancel() {
            DialogTools.getToastBuilder().toToast("点击取消")
        }
    })
```

#### 3.2.3.加载对话框
```kotlin
DialogTools.getDialogBuilder()
    .toLoadingDialog(this)
```
### 3.3.选择菜单
#### 3.3.1.底部单选菜单
```kotlin
DialogTools.getDialogBuilder()
    .setTitleText("重复")
    .toSingleMenu(
        this,
        mSingleList,
        mSelectSingleIndex,
        object : ISingleMenuCallback {
            override fun onPositive(title: String, menuIndex: Int) {
                mSelectSingleIndex = menuIndex
                DialogTools.getToastBuilder().toToast("选中内容:$title")
            }

            override fun onCancel() {
                DialogTools.getToastBuilder().toToast("点击取消")
            }
        })
```
#### 3.3.2.底部多选菜单
```kotlin
DialogTools.getDialogBuilder()
    .setTitleText("重复")
    .toMultipleMenu(this, mMultipleList, mSelectMultipleIndex,
        object : IMultipleMenuCallback {
            override fun onPositive(title: List<String>, menuIndex: List<Int>) {
                mSelectMultipleIndex.clear()
                mSelectMultipleIndex.addAll(menuIndex)
                DialogTools.getToastBuilder().toToast("选中内容:${menuIndex.size}个")
            }

            override fun onCancel() {
                DialogTools.getToastBuilder().toToast("点击取消")
            }
        })
```
#### 3.3.3.Pop菜单
```kotlin
DialogTools.getDialogBuilder()
    .setTouchCoordinate(event.x, event.y, event.rawX, event.rawY)
    .toPopMenu(this, v, mutableListOf("删除", "编辑", "复制"), object : IPopMenuCallback {
        override fun onPositive(title: String, menuIndex: Int) {
            DialogTools.getToastBuilder().toToast(title)
        }
    })
```

### 3.4.选择器
#### 3.4.1.日期时间选择器
```kotlin
DialogTools.getPickerBuilder()
    .setTitleText("请选择日期和时间")
    .setMinDateTime(mStartDateTime)
    .setMaxDateTime(mEndDateTime)
    .setSelDateTime(mSelectDateTime)
    .toDateTimePicker(this, PICKER_ALL, object : IDateTimeCallback {
        override fun onPositive(result: EaseDateTimeEntity) {
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

#### 3.4.2.地址选择器
```kotlin
DialogTools.getPickerBuilder()
    .setTitleText("请选择地址")
    .setSelectProvince(mProvince)
    .setSelectCity(mCity)
    .setSelectCounty(mCounty)
    .toAddressPicker(this, PICKER_ADDRESS_PROVINCE_CITY, object : ILocationCallback {
        override fun onPositive(
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
#### 3.4.2.颜色选择器
```kotlin
DialogTools.getPickerBuilder()
    .setTitleText("请选择颜色")
    .toColorPicker(this, object : IColorSelectCallback {
        override fun onPositive(red: Int, green: Int, blue: Int) {
            DialogTools.getToastBuilder()
                .toToast("R:".plus(red).plus("|G:").plus(green).plus("|B:").plus(blue))
        }
    })
```
### 3.5.其他
#### 3.5.1.Toast
```kotlin
DialogTools.getToastBuilder()
    .toToast("文件删除成功~")
```







