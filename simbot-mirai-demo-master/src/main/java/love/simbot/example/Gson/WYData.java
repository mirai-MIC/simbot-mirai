package love.simbot.example.Gson;

import java.util.List;

public class WYData {

    private String app;
    private String desc;
    private String view;
    private String ver;
    private String prompt;
    private String appID;
    private String sourceName;
    private String actionData;
    private String actionData_A;
    private String sourceUrl;
    private MetaDTO meta;
    private ConfigDTO config;
    private String text;
    private List<?> extraApps;
    private String sourceAd;
    private String extra;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getActionData() {
        return actionData;
    }

    public void setActionData(String actionData) {
        this.actionData = actionData;
    }

    public String getActionData_A() {
        return actionData_A;
    }

    public void setActionData_A(String actionData_A) {
        this.actionData_A = actionData_A;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public MetaDTO getMeta() {
        return meta;
    }

    public void setMeta(MetaDTO meta) {
        this.meta = meta;
    }

    public ConfigDTO getConfig() {
        return config;
    }

    public void setConfig(ConfigDTO config) {
        this.config = config;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<?> getExtraApps() {
        return extraApps;
    }

    public void setExtraApps(List<?> extraApps) {
        this.extraApps = extraApps;
    }

    public String getSourceAd() {
        return sourceAd;
    }

    public void setSourceAd(String sourceAd) {
        this.sourceAd = sourceAd;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public static class MetaDTO {
        private MusicDTO music;

        public MusicDTO getMusic() {
            return music;
        }

        public void setMusic(MusicDTO music) {
            this.music = music;
        }

        public static class MusicDTO {
            private String action;
            private String android_pkg_name;
            private int app_type;
            private int appid;
            private int ctime;
            private String desc;
            private String jumpUrl;
            private String musicUrl;
            private String preview;
            private String sourceMsgId;
            private String source_icon;
            private String source_url;
            private String tag;
            private String title;
            private long uin;

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getAndroid_pkg_name() {
                return android_pkg_name;
            }

            public void setAndroid_pkg_name(String android_pkg_name) {
                this.android_pkg_name = android_pkg_name;
            }

            public int getApp_type() {
                return app_type;
            }

            public void setApp_type(int app_type) {
                this.app_type = app_type;
            }

            public int getAppid() {
                return appid;
            }

            public void setAppid(int appid) {
                this.appid = appid;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getJumpUrl() {
                return jumpUrl;
            }

            public void setJumpUrl(String jumpUrl) {
                this.jumpUrl = jumpUrl;
            }

            public String getMusicUrl() {
                return musicUrl;
            }

            public void setMusicUrl(String musicUrl) {
                this.musicUrl = musicUrl;
            }

            public String getPreview() {
                return preview;
            }

            public void setPreview(String preview) {
                this.preview = preview;
            }

            public String getSourceMsgId() {
                return sourceMsgId;
            }

            public void setSourceMsgId(String sourceMsgId) {
                this.sourceMsgId = sourceMsgId;
            }

            public String getSource_icon() {
                return source_icon;
            }

            public void setSource_icon(String source_icon) {
                this.source_icon = source_icon;
            }

            public String getSource_url() {
                return source_url;
            }

            public void setSource_url(String source_url) {
                this.source_url = source_url;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getUin() {
                return uin;
            }

            public void setUin(long uin) {
                this.uin = uin;
            }
        }
    }

    public static class ConfigDTO {
        private int ctime;
        private boolean forward;
        private String token;
        private String type;

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public boolean isForward() {
            return forward;
        }

        public void setForward(boolean forward) {
            this.forward = forward;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}