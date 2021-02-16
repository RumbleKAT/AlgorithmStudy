class Solution {
    public String[] getFolderNames(String[] names) {
        int n = names.length;
        Map<String, Integer> next = new HashMap<>();
        Set<String> used = new HashSet<>();

        String[] res = new String[n];

        for (int i = 0; i < n; i++) {
            String name = names[i];
            if (used.contains(name)) {
                if (!next.containsKey(name)) {
                    next.put(name, 1);
                }
                while (used.contains(generate(name, next.get(name)))) {
                    System.out.println(next.get(name) + 1);
                    next.put(name, next.get(name) + 1);
                }
                System.out.println(name);
                String newName = generate(name, next.get(name));
                used.add(newName);
                res[i] = newName;
                next.put(name, next.get(name) + 1);
            } else {
                res[i] = name;
                used.add(name);
            }
        }

        return res;
    }

    public String generate(String prefix, int num) {
        return prefix + "(" + num + ")";
    }
}