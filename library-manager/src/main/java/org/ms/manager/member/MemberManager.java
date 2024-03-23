package org.ms.manager.member;

import java.util.Optional;
import org.ms.manager.auth.LoginSession;
import org.ms.manager.auth.MemberLoginManager;
import org.ms.member.LibraryCard;
import org.ms.member.LibraryMemberRepository;
import org.ms.member.Member;
import org.ms.utils.UniqueIdUtil;

public class MemberManager {

	private LibraryMemberRepository memberRepository;

	private MemberLoginManager memberLoginManager;

	public Optional<LibraryCard> addMember(String name, LoginSession loginSession) {
		if (!memberLoginManager.isValid(loginSession)) {
			return Optional.empty();
		}
		if(memberRepository.exists(name)){
			return Optional.empty();
		}
		var card = new LibraryCard(UniqueIdUtil.generateUniqueId());
		memberRepository.addMember(new Member(card, name));
		return Optional.of(card);
	}
}
