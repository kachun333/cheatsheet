class MVCResultResourceIT {
        ResultActions resultActions = mockMvc.perform(get("/api/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Map<String, Object> response = objectMapper.readValue(contentAsString, Map.class);
        List<Cateogries> categories = objectMapper.convertValue(
                response.get("categories"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cateogries.class)
        );
}