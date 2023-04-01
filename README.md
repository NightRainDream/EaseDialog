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
	implementation 'com.github.NightRainDream:EaseDialog:v1.0.1'
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
    .setPositiveText("继续")
    .toTipsDialog(this, "退出账号", "重之前，需验证您的账号和密码，以关闭手机查找等功能功。")
```

#### 3.2.2.警告性质对话框

```kotlin
DialogTools.getDialogBuilder()
    .setPositiveText("删除")
    .setPositiveTextColor(Color.RED)
    .toWarnDialog(this, "确定删除当前联系人?")
```

#### 3.2.3.加载对话框

```kotlin
DialogTools.getDialogBuilder().toLoadingDialog(this)
```

#### 3.2.4.单选菜单

```kotlin
DialogTools.getDialogBuilder()
    .toSingleMenu(
        this,
        "重复",
        mSingleList,
        mSelectSingleIndex,
        object : IDialogActionCallback {
            override fun onPositive(content: String, index: MutableList<Int>) {
                mSelectSingleIndex = index.first()
                Toast.makeText(this@MainActivity, content, Toast.LENGTH_SHORT).show()
            }
        })
```

#### 3.2.5.多选菜单

```kotlin
DialogTools.getDialogBuilder()
    .toMultipleMenu(this, "重复", mMultipleList, mSelectMultipleIndex,
        object : IDialogActionCallback {
            override fun onPositive(content: String, index: MutableList<Int>) {
                mSelectMultipleIndex.clear()
                mSelectMultipleIndex.addAll(index)
            }
        })
```

#### 3.2.6.日期选择器

```kotlin
DialogTools.getPickerBuilder()
    .toDatePicker(this, "请选择日期", object : IDateTimeSelectCallback {
        override fun onSelectDate(result: DateTimeEntity) {
            Toast.makeText(this@MainActivity, result.getDate(), Toast.LENGTH_SHORT).show()
        }
    })
```

#### 3.2.7.时间选择器

```kotlin
DialogTools.getPickerBuilder()
    .toTimePicker(this, "请选择时间", object : IDateTimeSelectCallback {
        override fun onSelectDate(result: DateTimeEntity) {
            Toast.makeText(this@MainActivity, result.getTime(), Toast.LENGTH_SHORT).show()
        }
    })
```







