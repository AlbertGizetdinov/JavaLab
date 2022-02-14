package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.dto.AccountDto;
import ru.itis.services.SearchService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SearchController {

    private final SearchService searchService;

    @RequestMapping("/search/users")
    public String getUsersSearchPage() {
        return "search_users";
    }

    @RequestMapping(value = "/search/users/byEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AccountDto> searchUsersByEmail(@RequestParam("email") String email) {
        return searchService.searchUsersByEmail(email);
    }
}
