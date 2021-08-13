package com.eldorado.userservice.auditconfigs;

import org.springframework.data.domain.AuditorAware;

import com.eldorado.userservice.constants.UserConstants;

import java.util.Optional;

/*
*In AuditorAwareImpl we can see that current implementation Eldorado Auditor is the the current auditor
*/
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(UserConstants.ELDORADO_AUDITOR);
	}

}