import java.util.Objects;

    private final UnifiedDiffLine DIFF_COMMAND = new UnifiedDiffLine(true, "^diff\\s", this::processDiff);
    private final UnifiedDiffLine INDEX = new UnifiedDiffLine(true, "^index\\s[\\da-zA-Z]+\\.\\.[\\da-zA-Z]+(\\s(\\d+))?$", this::processIndex);
    private final UnifiedDiffLine FROM_FILE = new UnifiedDiffLine(true, "^---\\s", this::processFromFile);
    private final UnifiedDiffLine TO_FILE = new UnifiedDiffLine(true, "^\\+\\+\\+\\s", this::processToFile);

    private final UnifiedDiffLine CHUNK = new UnifiedDiffLine(false, UNIFIED_DIFF_CHUNK_REGEXP, this::processChunk);
    private final UnifiedDiffLine LINE_NORMAL = new UnifiedDiffLine("^\\s+", this::processNormalLine);
    private final UnifiedDiffLine LINE_DEL = new UnifiedDiffLine("^-", this::processDelLine);
    private final UnifiedDiffLine LINE_ADD = new UnifiedDiffLine("^+", this::processAddLine);
        LOG.log(Level.INFO, "header parsing");
        String line = null;
            line = READER.readLine();
            LOG.log(Level.INFO, "parsing line {0}", line);
            if (DIFF_COMMAND.validLine(line) || INDEX.validLine(line)
                    || FROM_FILE.validLine(line) || TO_FILE.validLine(line)) {
                headerTxt += line + "\n";
            }
        }
        data.setHeader(headerTxt);

        while (line != null) {
            if (!CHUNK.validLine(line)) {
                processLine(line, DIFF_COMMAND, INDEX, FROM_FILE, TO_FILE);
            } else {
                processLine(line, CHUNK);
            line = READER.readLine();
        String tailTxt = "";
    private void processLine(String line, UnifiedDiffLine... rules) throws UnifiedDiffParserException {
        for (UnifiedDiffLine rule : rules) {
            if (rule.processLine(line)) {
                LOG.info("  >>> processed rule " + rule.toString());
                return;
        throw new UnifiedDiffParserException("parsing error at line " + line);
    private int old_size;
    private int new_size;
        old_ln = toInteger(match, 1, 1);
        old_size = toInteger(match, 2, 0);
        new_ln = toInteger(match, 3, 1);
        new_size = toInteger(match, 4, 0);
    private static Integer toInteger(MatchResult match, int group, int defValue) throws NumberFormatException {
        return Integer.valueOf(Objects.toString( match.group(group) , "" + defValue));
    }

    final class UnifiedDiffLine {
        public boolean validLine(String line) {
            Matcher m = pattern.matcher(line);
            return m.find();
        }
