class Solution {
    public String validIPAddress(String IP) {
        String [] st;
        int ch = -1;
        if(IP.equals("")) return "Neither";
        
        if(IP.charAt(IP.length()-1) == '.'){
            return "Neither";
        }else if(IP.charAt(IP.length()-1) == ':'){
            return "Neither";
        }
        
        if(IP.indexOf(".")!= -1){
            st = IP.split("\\.");
            ch = 1;
        }else if(IP.indexOf(":") != -1){
            st = IP.split(":");
            ch = 2;
        }
        else{
            return "Neither";
        }
        System.out.println(st.length);
        if(ch == 1){
            if(st.length != 4){
                return "Neither"; 
            }else{
                for(String cur : st){
                    System.out.println(cur);
                    if(cur == null || cur == " ") return "Neither";
                    try{
                        if(cur.length () > 1 && cur.charAt(0) == '0') return "Neither";
                        if(!check(Integer.parseInt(cur))){
                            return "Neither";
                        }   
                    }catch(Exception e){
                        return "Neither";
                    }
                }
                return "IPv4";
            }    
        }else if(ch == 2){
            if(st.length != 8){
                return "Neither"; 
            }else{
                for(String cur : st){
                    if(cur == "") return "Neither";
                    try{
                        // if(cur.length() > 1){
                        //     if(cur.charAt(0) == '0') return "Neither";
                        // }
                            // if(cur.charAt(0) == '0') return "Neither";
                        if(!check2(cur)){
                            return "Neither";
                        }
                    }catch(Exception e){
                        return "Neither";
                    }
                }
                return "IPv6";
            }
        }
        return "Neither";
    }
    
    
    public boolean check(int param){
        if(0 <= param && param <= 255){
            return true; 
        }else{
            return false;
        }
    }
    
    public boolean check2(String param){
        if(param.length() > 4) return false;
        
        int decimal = 0;
        try{
            decimal = Integer.parseInt(param, 16);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}