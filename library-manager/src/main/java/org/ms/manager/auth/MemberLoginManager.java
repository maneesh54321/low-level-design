package org.ms.manager.auth;

import java.util.HashSet;
import java.util.Set;
import org.ms.manager.exception.InvalidMemberException;
import org.ms.member.LibraryMember;
import org.ms.member.LibraryMemberRepository;
import org.ms.member.LoginCapable;
import org.ms.utils.UniqueIdUtil;

public class MemberLoginManager {

	private final LibraryMemberRepository memberRepository;

	private Set<LoginSession> loginSessions;

	public MemberLoginManager(LibraryMemberRepository memberRepository) {
		this.memberRepository = memberRepository;
		loginSessions = new HashSet<>();
	}

	public LoginSession login(Credential credential){
		String id = credential.id();
		return memberRepository.getMember(id)
				.filter(member -> member instanceof LoginCapable)
				.map(LoginCapable.class::cast)
				.filter(member -> member.getPassword().equals(credential.password()))
				.map(LibraryMember.class::cast)
				.map(member -> {
					var session = new LoginSession(member, UniqueIdUtil.generateUniqueId());
					loginSessions.add(session);
					return session;
				})
				.orElseThrow(() -> new InvalidMemberException("Invalid user id"));
	}

	public boolean isValid(LoginSession loginSession){
		return loginSessions.contains(loginSession);
	}
}
