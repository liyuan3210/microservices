

public class WechatResult {

	private String appId;
	private String basicToken;
	private String accessToken;
	private String ticket;
	private String timestamp;
	private String noncestr;
	private String signature;
	private String openid;

	public String getAppId(){
		return appId;
	}

	public void setAppId(String appId){
		this.appId=appId;
	}

	public String getBasicToken(){
		return basicToken;
	}

	public void setBasicToken(String basicToken){
		this.basicToken=basicToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setAccessToken(String accessToken){
		this.accessToken=accessToken;
	}

	public String getTicket(){
		return ticket;
	}

	public void setTicket(String ticket){
		this.ticket=ticket;
	}

	public String getTimestamp(){
		return timestamp;
	}

	public void setTimestamp(String timestamp){
		this.timestamp=timestamp;
	}

	public String getNoncestr(){
		return noncestr;
	}

	public void setNoncestr(String noncestr){
		this.noncestr=noncestr;
	}

	public String getSignature(){
		return signature;
	}

	public void setSignature(String signature){
		this.signature=signature;
	}

	public String getOpenid(){
		return openid;
	}

	public void setOpenid(String openid){
		this.openid=openid;
	}
}
