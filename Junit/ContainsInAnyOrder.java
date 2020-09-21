class ContainsInAnyOrderExample {

    @Test
    public void testJSONPathArray() {
        Integer[] ids = xxxList
                .stream()
                .map(Xxx::getId)
                .map(Long::intValue)
                .toArray(Integer[]::new);

        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(containsInAnyOrder(ids)))
                .andExpect(jsonPath("$.[*].xxx").value(hasItem(DEFAULT_VALUE_XXX)))
    }
}