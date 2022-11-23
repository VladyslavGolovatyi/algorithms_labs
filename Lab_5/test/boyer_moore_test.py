import time
import unittest

from Lab_5.boyer_moore import write_to_file, find_pattern_in_text

worst_case_pattern = 'bbbbbbbbbb'
best_case_pattern = 'riteruisdh'
avg_case_pattern = "bsbabsbbsb"


class MyTestCase(unittest.TestCase):
    def test_bm_worst_case(self):
        file = open("../Lab_5.resources/worst_case.txt", 'r')
        text = file.read()

        start = time.perf_counter()
        end_indexes = find_pattern_in_text(worst_case_pattern, text)
        end = time.perf_counter()

        write_to_file(end_indexes, "results/worst_case_result.txt", len(worst_case_pattern))
        print(f"Time taken: {(end - start) * 10 ** 3:.4f}ms - worst case")

    def test_bm_best_case(self):
        file = open("../Lab_5.resources/best_case.txt", 'r')
        text = file.read()

        start = time.perf_counter()
        end_indexes = find_pattern_in_text(best_case_pattern, text)
        end = time.perf_counter()

        write_to_file(end_indexes, "results/best_case_result.txt", len(best_case_pattern))
        print(f"Time taken: {(end - start) * 10 ** 3:.4f}ms - best case")

    def test_bm_average_case(self):
        file = open("../Lab_5.resources/avg_case.txt", 'r')
        text = file.read()

        start = time.perf_counter()
        end_indexes = find_pattern_in_text(avg_case_pattern, text)
        end = time.perf_counter()

        write_to_file(end_indexes, "results/avg_case_result.txt", len(avg_case_pattern))
        print(f"Time taken: {(end - start) * 10 ** 3:.4f}ms - average case")


if __name__ == '__main__':
    unittest.main()
