package statusbar.lyric.activity.page

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import statusbar.lyric.tools.ActivityTools.changeConfig

@BMPage
class ExtendPage : BasePage() {
    override fun onCreate() {
        TextSw("Hide Notification Icon", key = "hideNotificationIcon", onClickListener = { changeConfig() })
    }
}