package py.com.sodep.mf.exchange.objects.auth;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Object with user's credentials")
public class MFAuthenticationRequest {

	private String user;

	private String password;

	private Long applicationId;

	@ApiModelProperty(required = true, value = "User to login")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@ApiModelProperty(required = true, value = "Password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ApiModelProperty(value = "Application to login to. If not provided, the server will "
			+ "respond with a list of possible applications")
	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

}
