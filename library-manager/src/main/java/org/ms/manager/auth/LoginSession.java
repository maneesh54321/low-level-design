package org.ms.manager.auth;

import org.ms.member.LibraryMember;

public record LoginSession(LibraryMember member, String token) {
}
