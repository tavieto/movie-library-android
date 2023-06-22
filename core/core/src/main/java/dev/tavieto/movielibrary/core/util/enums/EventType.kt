package dev.tavieto.movielibrary.core.util.enums

enum class EventType(val text: String) {
    CHANGE_MODEL("r2u_change_model"),
    CHANGE_MATERIAL("r2u_change_material"),
    FOCUS_OBJECT("r2u_focus_object"),
    CHANGE_FRAMING("r2u_change_framing"),
    MOVE_OBJECT("r2u_move_object"),
    CHANGE_VISIBILITY("r2u_change_visibility"),
    RESET_CAMERA("r2u_reset_camera"),
    SHOW_PINS("r2u_show_pins_spots"),
    HIDE_PINS("r2u_hide_pins_spots"),
    TAKE_SNAPSHOT("r2u_take_snapshot"),
    ROTATION_MODEL("r2u_rotate_models"),
    SETUP_SCENE("r2u_scene_setup"),
    RESET_MODEL("r2u_reset_customization"),
    MODEL_LOADING("MODEL_LOADING_COMPLETE"),
    CAMERA_MOVING("CAMERA_MOVING"),
    PIN_CLICKED("PIN_CLICKED"),
    SNAPSHOT_DATA("SNAPSHOT_DATA")
}
