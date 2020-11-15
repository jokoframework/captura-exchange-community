package py.com.sodep.mf.exchange.objects.auth;

import java.util.List;

import py.com.sodep.mf.exchange.objects.metadata.Application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
@ApiModel(value = "Server response to a login request")
public class MFAuthenticationResponse {

	private boolean success;

	private List<Application> possibleApplications;

	private Application application;

	@ApiModelProperty(value = "True only if the user successfully logins to an application", required = true)
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@ApiModelProperty(value = "If an application is not provided in the request but the credentials were OK,"
			+ " a list of possible applications is returned")
	public List<Application> getPossibleApplications() {
		return possibleApplications;
	}

	public void setPossibleApplications(List<Application> applications) {
		this.possibleApplications = applications;
	}

	@ApiModelProperty(value = "Application to which the user logged in. "
			+ "May be null if no applicationId was provided")
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}
