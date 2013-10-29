package com.wy.shopping.pojo;

import net.tsz.afinal.annotation.sqlite.Property;
import net.tsz.afinal.annotation.sqlite.Table;
import net.tsz.afinal.annotation.sqlite.Transient;

import com.wy.shopping.utils.Excluded;


@Table(name = "USERINFO")
public class UserInfo  {

    @Excluded
    @Transient
    private static final long serialVersionUID = 9093933074485063604L;

    @Excluded
    @Property(column = "ID")
    private int id;

    /** 用户序号 */
    @Property(column = "UID")
    private int uid;
    
    /** 用户名 */
    @Property(column = "USERNAME")
    private String userName;

    /** 密码 */
    @Property(column = "PWD")
    private String pwd;

    /** 会话id，登录成功后由服务端设置 */
    @Property(column = "SESSIONID")
    private String sessionId;

    /** 用户状态 */
    @Property(column = "STATE")
    private Integer state;

    /** 用户使用的apk版本号 */
    @Excluded
    @Property(column = "APKVERSION")
    private String apkVersion;

    /** 昵称 */
    @Property(column = "NIKENAME")
    private String nikeName;

    /** 个性签名 */
    @Property(column = "SIGN")
    private String sign;

    public UserInfo() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getApkVersion() {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", uid=" + uid + ", userName=" + userName
                + ", pwd=" + pwd + ", sessionId=" + sessionId + ", state="
                + state + ", apkVersion=" + apkVersion + ", nikeName="
                + nikeName + ", sign=" + sign + "]";
    }

}
