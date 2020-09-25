
    private void checkNoDuplicatesInList(List<XObject> xObject) {
        Set<Long> uniqueIds = new HashSet<>();
        Optional<Long> duplicateId = xObject
                .stream()
                .map(XObject::getId)
                .filter(n -> !uniqueIds.add(n)) // Set.add() returns false if ady exists
                .findFirst();
        if (duplicateId.isPresent()) {
            throw new Exception("Duplicate found: " + duplicateId.get());
        }
    }
    
    private List<XObject> addToListIfDoesNotExists(List<XObject> list1, List<XObject> list2) {

        Set<Long> uniqueIds = list1
                .stream()
                .map(XObject::getId)
                .collect(Collectors.toSet());

        List<XObject> itemsThatINeedToAdd = list2
                .stream()
                .filter(n -> uniqueIds.add(n.getId()))  // Set.add() returns true if not exists
                .collect(Collectors.toList());

        return Stream
                .concat(list1.stream(), itemsThatINeedToAdd.stream())
                .collect(Collectors.toList());
    }