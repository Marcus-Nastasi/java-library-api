package com.library.app.DTOs.Members;

import com.library.app.Enums.Members.MemberType;

public record NewMemberDTO(String name, String cpf, MemberType type, String phone, int booksIssued, int booksLimit) {
}


