def find_pattern_in_text(pattern: str, text: str):
    end_indexes = []
    current_text_index = len(pattern) - 1
    while current_text_index < len(text):
        current_pattern_index = len(pattern) - 1
        temp_match = ""
        for i in range(current_text_index, current_text_index - len(pattern), -1):
            if text[i] == pattern[current_pattern_index]:
                temp_match = pattern[current_pattern_index] + temp_match
                if len(temp_match) == len(pattern):
                    current_text_index += 1
                    break
            else:
                step = 0
                j = current_pattern_index
                while j >= 0:
                    if text[i] == pattern[j]:
                        step = len(pattern) - len(temp_match) - j - 1
                        break
                    j -= 1
                if j == -1:
                    step = len(pattern) - len(temp_match)
                current_text_index += step
                break
            current_pattern_index -= 1
        if temp_match == pattern:
            end_indexes.append(current_text_index)

    return end_indexes


def write_to_file(end_indexes: [], file_out: str, pattern_len: int):
    file = open(file_out, 'w+')
    try:
        for i in end_indexes:
            file.write("Pattern matched at index {} to {}".format(i-pattern_len, i))
            file.write("\n")
    finally:
        file.close()
