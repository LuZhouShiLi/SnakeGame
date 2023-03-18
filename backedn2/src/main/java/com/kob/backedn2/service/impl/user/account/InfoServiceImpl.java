package com.kob.backedn2.service.impl.user.account;
import com.kob.backedn2.pojo.User;
import com.kob.backedn2.service.impl.utils.UserDetailsImpl;
import com.kob.backedn2.service.user.account.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public Map<String, String> getInfo() {
         UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
         UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
         User user = loginUser.getUser();
         Map<String,String> map = new HashMap<>();
         map.put("error_message","success");
         map.put("id",user.getId().toString());
         map.put("username",user.getUsername());
         map.put("photo",user.getPhoto());
         return map;
    }
}
